package kg.easy.finalproject.services.impl;

import kg.easy.finalproject.dao.ProductRepository;
import kg.easy.finalproject.mappers.ProductMapper;
import kg.easy.finalproject.models.dto.ProductDto;
import kg.easy.finalproject.models.entities.Product;
import kg.easy.finalproject.models.responses.ErrorResponse;
import kg.easy.finalproject.services.ProductService;
import kg.easy.finalproject.services.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<?> saveProduct(String token, ProductDto productDto) {
        ResponseEntity<?>responseEntity=userService.verifyLogin(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        Product product = ProductMapper.INSTANCE.mapToProduct(productDto);
        if (Objects.nonNull(productRepository.findByNameOrBarcode(product.getName(), product.getBarcode()))){
            productRepository.save(product);
        }else{
            return  new ResponseEntity<>(new ErrorResponse("Такой товар уже существует", null), HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok("Товар успешно сохранен!" + ProductMapper.INSTANCE.mapToProductDto(product));
    }

    @Override
    public ResponseEntity<?> getProductByBarcode(String token, String barcode) {
        ResponseEntity<?> responseEntity = userService.verifyLogin(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        Product product = productRepository.findByBarcode(barcode);
        if (Objects.nonNull(product)){
            return new ResponseEntity<>(new ErrorResponse("Товара с таким штрихкодои нет", null), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(ProductMapper.INSTANCE.mapToProductDto(product));
    }

    @Override
    public ResponseEntity<?> getAllProducts(String token) {
        ResponseEntity<?> responseEntity = userService.verifyLogin(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        List<Product> productList = productRepository.findAll();
        List<ProductDto> productDtoList = productList.stream().map(x->ProductMapper.INSTANCE.mapToProductDto(x)).collect(Collectors.toList());
        return ResponseEntity.ok(productDtoList);
}

    @Override
    public ProductDto findProductByBarcodeForOperationDetails(String barcode) {
        return ProductMapper.INSTANCE.mapToProductDto(productRepository.findByBarcode(barcode));
    }

    @Override
    public ResponseEntity<?> updateProduct(String token, ProductDto productDto) {
        ResponseEntity<?>responseEntity= userService.verifyLogin(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        Product product = ProductMapper.INSTANCE.mapToProduct(productDto);
        productRepository.save(product);
        return ResponseEntity.ok("Товар успешно обновленн" + ProductMapper.INSTANCE.mapToProductDto(product));
    }
}
