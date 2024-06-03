package com.souldev.cart.controllers;
import com.souldev.cart.entities.Message;
import com.souldev.cart.entities.Product;
//import com.souldev.cart.services.IUploadFileService;
import com.souldev.cart.services.ProductService;
import com.souldev.cart.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import java.util.List;
//import java.nio.charset.StandardCharsets;
//import java.net.MalformedURLException;
//import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    /*@Autowired
    private IUploadFileService uploadFileService;*/

    @Autowired
    private ProductService productService;

    @Autowired
    public ProductController(IProductService iProductService) {
        this.iProductService = iProductService;
    }

    

    @GetMapping("/search")
    public List<Product> buscarProductos
    (@RequestParam String searchTerm) {
        return iProductService.getProductByName(searchTerm);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById
    (@PathVariable("id")String id){
        Optional<Product> productOptional = 
        this.iProductService.getProductById(id);
        if (productOptional.isEmpty())
            return new ResponseEntity<>
            (new Message("No encontrado"), 
            HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(productOptional.get(),
        HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<Object> getAllProducts(){
        return new ResponseEntity<>
        (this.iProductService.getAllProducts(),HttpStatus.OK);
    }

    /*
    @GetMapping("/best")
    public ResponseEntity<List<Product>> getBestProducts(){
        return new ResponseEntity<>(this.productService.getBestPriceProducts(),HttpStatus.OK);
    }*/


    @GetMapping("/related/{category}/{id}")
    public ResponseEntity<Object> getRelatedProducts
    (@PathVariable("category")String category, 
    @PathVariable("id")String id){
        return new ResponseEntity<>
        (this.iProductService.getRelatedProducts
        (category,id),HttpStatus.OK);
    }

    
    @PostMapping(consumes = 
    { MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Message> createProduct
    (@Valid @RequestPart("product") String stringProduct, 
    BindingResult bindingResult){
    try {
        Product product = iProductService.
        convertJsonToProduct(stringProduct);
        this.iProductService.saveProduct(product);
        return new ResponseEntity<>
        (new Message("Actualizado correctamente")
        ,HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(new Message
        ("Revise los campos"),
        HttpStatus.BAD_REQUEST);
    }
    }

    @GetMapping("/subcategory/{sub}")
    public ResponseEntity<Object> getSubCategory
    (@PathVariable("sub")String sub){
        return new ResponseEntity<>
        (this.productService.getAllProductsBySubCategoryName
        (sub),HttpStatus.OK);
    }




    /*@PutMapping("/update")
    public ResponseEntity<Message> updateProduct(@Valid @RequestBody Product product, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new Message("Revise los campos"),HttpStatus.BAD_REQUEST);
        this.iProductService.saveProduct(product);
        return new ResponseEntity<>(new Message("Actualizado correctamente"),HttpStatus.OK);
    }*/
}







/*if (bindingResult.hasErrors())
return new ResponseEntity<>(new Message("Revise los campos"),HttpStatus.BAD_REQUEST);
Product product = iProductService.convertJsonToProduct(stringProduct);
if(!image.isEmpty()){
if(product.getId() > 0 && product.getImage() != null & product.getImage().length()>0){
    uploadFileService.delete(product.getImage());
}
String image = uploadFileService.copy(image);
product.setImage(image);
}
this.iProductService.saveProduct(product);
return new ResponseEntity<>(new Message("Creado correctamente"),HttpStatus.OK);*/

    /*

    ·····················································································································

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Message> createProduct(@Valid @RequestPart("product") String stringProduct, BindingResult bindingResult){
    try {
        Product product = iProductService.convertJsonToProduct(stringProduct);
        this.iProductService.saveProduct(product);
        return new ResponseEntity<>(new Message("Actualizado correctamente"),HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(new Message("Revise los campos"),HttpStatus.BAD_REQUEST);
    }
    }

    ···········································································································

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Message> createProduct(@Valid @RequestPart("product") MultipartFile productFile, BindingResult bindingResult) {
        try {
            // Obtén la cadena JSON de MultipartFile
            String stringProduct = new String(productFile.getBytes(), StandardCharsets.UTF_8);

            Product product = iProductService.convertJsonToProduct(stringProduct);
            this.iProductService.saveProduct(product);
            return new ResponseEntity<>(new Message("Actualizado correctamente"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message("Revise los campos"), HttpStatus.BAD_REQUEST);
        }
    }

    ··············································································································3
    #ORIGINAL
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Message> createProduct(@Valid @RequestPart("product") String stringProduct, @RequestPart("file") MultipartFile image,@RequestPart("file2") MultipartFile pdf, BindingResult bindingResult){
    try {
        Product product = iProductService.convertJsonToProduct(stringProduct);
        if(!image.isEmpty()){
            if(product.getId() != null && product.getImage() != null && product.getImage().length()>0){
                uploadFileService.delete(product.getImage());
            }
            String imageUrl = uploadFileService.copy(image);
            product.setImage(imageUrl);
            String pdfUrl = uploadFileService.copy(pdf);
            product.setPdf(pdfUrl);
        }   
        this.iProductService.saveProduct(product);
        return new ResponseEntity<>(new Message("Actualizado correctamente"),HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(new Message("Revise los campos"),HttpStatus.BAD_REQUEST);
    }
    }
    
    ···············································································································
    
    @GetMapping(value = "/uploads/{filename}")
	public ResponseEntity<Resource> goImage(@PathVariable String filename) {
        Resource resource = null;
		try {
			resource = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename='\' + resource.getFilename() + '\'")
        .body(resource);
	}*/

