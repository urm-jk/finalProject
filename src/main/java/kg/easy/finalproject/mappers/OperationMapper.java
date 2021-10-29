package kg.easy.finalproject.mappers;

import kg.easy.finalproject.models.dto.OperationDto;
import kg.easy.finalproject.models.entities.Operation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OperationMapper {
    OperationMapper INSTANCE = Mappers.getMapper(OperationMapper.class);

    Operation mapToOperation(OperationDto operationDto);

    OperationDto mapToOperationDto(Operation operation);
}
