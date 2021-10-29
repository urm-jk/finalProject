package kg.easy.finalproject.mappers;

import kg.easy.finalproject.models.dto.RequestDto;
import kg.easy.finalproject.models.entities.Request;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface RequestMapper {

    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    @Mapping(source = "code.id", target = "code.id")
    Request mapToRequest(RequestDto requestDto);

    @Mapping(source = "code.id", target = "code.id")
    RequestDto mapToRequestDto(Request request);
}
