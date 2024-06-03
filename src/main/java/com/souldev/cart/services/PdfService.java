package com.souldev.cart.services;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.souldev.cart.entities.ShoppingCart;

import java.io.*;
@Service
public class PdfService {
    
    private static final String PDF_RESOURCES = "/pdf-resources/";

    private SpringTemplateEngine springTemplateEngine;

    private ShoppingCartService shoppingCartService;


    @Autowired
    public PdfService(SpringTemplateEngine springTemplateEngine, ShoppingCartService shoppingCartService) {
        this.springTemplateEngine = springTemplateEngine;
        this.shoppingCartService = shoppingCartService;
    }
    

    public File generatePlacesPdf(String userName) throws Exception{
        Context context = getContextPlaceListPdf(userName);
        String html = loadAndFillTemplate(context);
        String xhtml = convertToXhtml(html);
        return renderPlaceListPdf(xhtml);
    }

    private String convertToXhtml(String html) throws UnsupportedEncodingException {
        Tidy tidy = new Tidy();
        tidy.setXHTML(true);
        tidy.setIndentContent(true);
        tidy.setPrintBodyOnly(true);
        tidy.setInputEncoding("UTF-8");
        tidy.setOutputEncoding("UTF-8");
        tidy.setSmartIndent(true);
        tidy.setShowWarnings(false);
        tidy.setQuiet(true);
        tidy.setTidyMark(false);

        Document htmlDOM = tidy.parseDOM(new ByteArrayInputStream(html.getBytes()), null);

        OutputStream out = new ByteArrayOutputStream();
        tidy.pprint(htmlDOM, out);
        return out.toString();
    }
    private File renderPlaceListPdf(String html) throws Exception {
        File file = File.createTempFile("places", ".pdf");
        OutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
        renderer.setDocumentFromString(html, new ClassPathResource(PDF_RESOURCES).getURL().toExternalForm());
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        file.deleteOnExit();
        return file;
    }


    private Context getContextPlaceListPdf(String userName) {
        List<ShoppingCart> placeList = this.shoppingCartService.getListByClient(userName);
        Context context = new Context();
        context.setVariable("places", placeList);
        return context;
    }

    
    private String loadAndFillTemplate(Context context) {
        return springTemplateEngine.process("placesPDF", context);
    }

}