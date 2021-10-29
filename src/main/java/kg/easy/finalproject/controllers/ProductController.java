package kg.easy.finalproject.controllers;

import kg.easy.finalproject.models.dto.ProductDto;
import kg.easy.finalproject.services.ProductService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<?> saveProduct(@RequestHeader String token, @RequestBody ProductDto productDto){
        return productService.updateProduct(token, productDto);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestHeader String token, @RequestBody ProductDto productDto){
        return productService.updateProduct(token, productDto);
    }

    @GetMapping("/getByBarcode")
    public ResponseEntity<?> getProductByBarcode(@RequestHeader String token, @RequestParam String barcode){
        return productService.getProductByBarcode(token, barcode);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<?> getAllProducts(@RequestHeader String token){
        return productService.getAllProducts(token);
    }
}
