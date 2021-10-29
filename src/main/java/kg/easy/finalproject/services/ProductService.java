package kg.easy.finalproject.services;

import kg.easy.finalproject.models.dto.ProductDto;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<?> saveProduct(String token, ProductDto productDto);
    ResponseEntity<?> getProductByBarcode(String token, String barcode);
    ResponseEntity<?> getAllProducts(String token);
    ProductDto findProductByBarcodeForOperationDetails(String barcode);
    ResponseEntity<?> updateProduct(String token, ProductDto productDto);
}
