package kg.easy.finalproject.services;

import kg.easy.finalproject.models.dto.PriceDto;
import kg.easy.finalproject.models.dto.ProductDto;
import org.springframework.http.ResponseEntity;

public interface PriceService {
    ResponseEntity<?> savePrice(String token, PriceDto priceDto);
    ResponseEntity<?> getPriceByProduct(String token, ProductDto priceDto);
    ResponseEntity<?> getAllPrices(String token);
    double findPriceByProductForOperationDetails(ProductDto priceDto);
}
