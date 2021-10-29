package kg.easy.finalproject.services;

import kg.easy.finalproject.models.dto.OperationDetailDto;

import java.util.List;

public interface OperationDetailsService {
    void saveOperationDetails(List<OperationDetailDto> operationDetailDtoList);
}
