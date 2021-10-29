package kg.easy.finalproject.services.impl;

import kg.easy.finalproject.dao.DiscountRepository;
import kg.easy.finalproject.mappers.DiscountMapper;
import kg.easy.finalproject.mappers.ProductMapper;
import kg.easy.finalproject.models.dto.DiscountDto;
import kg.easy.finalproject.models.dto.ProductDto;
import kg.easy.finalproject.models.entities.Discount;
import kg.easy.finalproject.models.entities.Product;
import kg.easy.finalproject.models.responses.ErrorResponse;
import kg.easy.finalproject.services.DiscountService;
import kg.easy.finalproject.services.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    DiscountRepository discountRepository;

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<?> saveDiscount(String token, DiscountDto discountDto) {
        ResponseEntity<?> responseEntity = userService.verifyLogin(token);
        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        List<Discount> discountList = discountRepository.findAllByProduct(ProductMapper.INSTANCE.mapToProduct(discountDto.getProduct()));
        if (Objects.isNull(discountList) && !discountList.isEmpty()){
            discountList.stream().filter(oldDiscount -> oldDiscount.getStartDate().before(discountDto.getStartDate())
                    || oldDiscount.getStartDate().after(discountDto.getStartDate())
                    && oldDiscount.getEndDate().before(discountDto.getEndDate())
                    || oldDiscount.getEndDate().after(discountDto.getEndDate())).
                    forEach(oldDiscount -> {
                        oldDiscount.setEndDate(new Date());
                        discountRepository.save(oldDiscount);
                    });
        }
        Discount discount = DiscountMapper.INSTANCE.mapToDiscount(discountDto);
        discount = discountRepository.save(discount);
        return ResponseEntity.ok(DiscountMapper.INSTANCE.mapToDiscountDto(discount));
    }

    @Override
    public ResponseEntity<?> findDiscountByProduct(String token, ProductDto productDto) {
        ResponseEntity<?> responseEntity = userService.verifyLogin(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }
        Discount discount = discountRepository.findActualDiscount(ProductMapper.INSTANCE.mapToProduct(productDto).getId());
        if (Objects.isNull(discount)) {
            return new ResponseEntity<>(new ErrorResponse("Вы ввели неправльно данные", null), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(DiscountMapper.INSTANCE.mapToDiscountDto(discount));
    }

    @Override
    public ResponseEntity<?> getAllDiscounts(String token) {
        ResponseEntity<?> responseEntity = userService.verifyLogin(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        List<Discount> discountList = discountRepository.findAll();
        List<DiscountDto>discountDtoList = discountList.stream().map(x->DiscountMapper.INSTANCE.mapToDiscountDto(x)).collect(Collectors.toList());
        return ResponseEntity.ok(discountDtoList);
    }

    @Override
    public double getDiscountByProduct(ProductDto productDto) {
        Discount discount = discountRepository.findActualDiscount(productDto.getId());
        if (Objects.isNull(discount)){
            return 0;
        }
        return discount.getDiscount();
    }
}
