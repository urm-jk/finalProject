package kg.easy.finalproject.services;

import kg.easy.finalproject.models.dto.DiscountDto;
import kg.easy.finalproject.models.dto.ProductDto;
import org.springframework.http.ResponseEntity;

public interface DiscountService {
    ResponseEntity<?> saveDiscount(String token, DiscountDto discountDto);
    ResponseEntity<?> findDiscountByProduct(String token, ProductDto productDto);
    ResponseEntity<?> getAllDiscounts(String token);
    double getDiscountByProduct(ProductDto productDto);

}
