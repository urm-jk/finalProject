package kg.easy.finalproject.mappers;

import kg.easy.finalproject.models.dto.ProductDto;
import kg.easy.finalproject.models.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product mapToProduct(ProductDto productDto);

    ProductDto mapToProductDto(Product product);
}
