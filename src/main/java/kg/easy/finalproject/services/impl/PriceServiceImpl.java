package kg.easy.finalproject.services.impl;

import kg.easy.finalproject.dao.PriceRepository;
import kg.easy.finalproject.mappers.PriceMapper;
import kg.easy.finalproject.mappers.ProductMapper;
import kg.easy.finalproject.models.dto.PriceDto;
import kg.easy.finalproject.models.dto.ProductDto;
import kg.easy.finalproject.models.entities.Price;
import kg.easy.finalproject.models.entities.Product;
import kg.easy.finalproject.models.responses.ErrorResponse;
import kg.easy.finalproject.services.PriceService;
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
public class PriceServiceImpl implements PriceService {
    @Autowired
    PriceRepository priceRepository;

    @Autowired
    UserService userService;
    @Override
    public ResponseEntity<?> savePrice(String token, PriceDto priceDto) {
        ResponseEntity<?> responseEntity = userService.verifyLogin(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
        return responseEntity;
        }
        Product product = ProductMapper.INSTANCE.mapToProduct(priceDto.getProduct());
        List<Price> priceList = priceRepository.findAllByProduct(product);
        if (Objects.nonNull(priceList) && !priceList.isEmpty()){
            priceList.stream().filter(x->x.getStartDate().before(priceDto.getStartDate())
                    || x.getStartDate().after(priceDto.getStartDate())
                    && x.getStartDate().before(priceDto.getEndDate())
                    || x.getStartDate().after(priceDto.getEndDate()))
                    .forEach(x-> {
                        x.setEndDate(new Date());
                        priceRepository.save(x);
                    });
        }
        Price price = PriceMapper.INSTANCE.mapToPrice(priceDto);
        price = priceRepository.save(price);
        return ResponseEntity.ok(PriceMapper.INSTANCE.mapToPriceDto(price));
    }

    @Override
    public ResponseEntity<?> getPriceByProduct(String token, ProductDto productDto) {
        ResponseEntity<?> responseEntity = userService.verifyLogin(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }
        Price price = priceRepository.findActualPrice(ProductMapper.INSTANCE.mapToProduct(productDto));
        if (Objects.nonNull(price)) {
            return new ResponseEntity<>(new ErrorResponse("Вы ввели неккоренктные данные", null), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(PriceMapper.INSTANCE.mapToPriceDto(price));
    }

    @Override
    public ResponseEntity<?> getAllPrices(String token) {
        ResponseEntity<?> responseEntity = userService.verifyLogin(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }
        List<Price> priceList = priceRepository.findAll();
        List<PriceDto> priceDtoList = priceList.stream().map(x->PriceMapper.INSTANCE.mapToPriceDto(x)).collect(Collectors.toList());
        return ResponseEntity.ok(priceDtoList);
    }

    @Override
    public double findPriceByProductForOperationDetails(ProductDto productDto) {
        Price actualPrice = priceRepository.findActualPrice(ProductMapper.INSTANCE.mapToProduct(productDto));

        return actualPrice.getPrice();
    }
}
