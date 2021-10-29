package kg.easy.finalproject.mappers;

import kg.easy.finalproject.models.dto.OperationDetailDto;
import kg.easy.finalproject.models.entities.OperationDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OperationDetailMapper {
    OperationDetailMapper INSTANCE = Mappers.getMapper(OperationDetailMapper.class);

    OperationDetail mapToOperationDetail(OperationDetailDto operationDetailDto);

    OperationDetailDto mapToOperationDetailDto(OperationDetail operationDetail);
}
