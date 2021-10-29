package kg.easy.finalproject.mappers;

import kg.easy.finalproject.models.dto.DiscountDto;
import kg.easy.finalproject.models.entities.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DiscountMapper {
    DiscountMapper INSTANCE = Mappers.getMapper(DiscountMapper.class);

    Discount mapToDiscount(DiscountDto discountDto);

    DiscountDto mapToDiscountDto(Discount discount);
}
