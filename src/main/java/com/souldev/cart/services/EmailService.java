package com.souldev.cart.services;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
@Transactional
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    PdfService pdfService;
    @Value("${spring.mail.username}")
    private String email;

    public void sendListEmail(String emailTo, String userName){
        MimeMessage message= javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            File file = pdfService.generatePlacesPdf(userName);
            helper.setFrom(email);
            helper.setTo(emailTo);
            helper.setSubject("listado");
            helper.setText("estimado cliente adjuntamos el listado correspondiente");
            helper.addAttachment("Listado", file);
            javaMailSender.send(message);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}