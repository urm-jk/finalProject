package kg.easy.finalproject.controllers;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kg.easy.finalproject.models.dto.DiscountDto;
import kg.easy.finalproject.models.dto.ProductDto;
import kg.easy.finalproject.services.DiscountService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/discounts")
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class DiscountController {
    @Autowired
    DiscountService discountService;

    @PostMapping("/save")
    public ResponseEntity<?> saveDiscount(@RequestHeader String token, @RequestBody  DiscountDto discountDto){
        return discountService.saveDiscount(token, discountDto);
    }

    @PostMapping("/getByProduct")
    public ResponseEntity<?> getByProduct(@RequestHeader String token, @RequestBody ProductDto productDto){
        return discountService.findDiscountByProduct(token, productDto);
    }
    @GetMapping("/getAllDiscount")
    public ResponseEntity<?> getAllDiscount(@RequestHeader String token){
        return discountService.getAllDiscounts(token);
    }
}
