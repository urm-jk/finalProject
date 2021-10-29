package kg.easy.finalproject.mappers;

import kg.easy.finalproject.models.dto.PriceDto;
import kg.easy.finalproject.models.entities.Price;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PriceMapper {
    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    Price mapToPrice(PriceDto priceDto);

    PriceDto mapToPriceDto(Price price);
}
