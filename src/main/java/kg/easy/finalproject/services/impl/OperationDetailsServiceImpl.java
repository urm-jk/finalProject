package kg.easy.finalproject.services.impl;

import kg.easy.finalproject.dao.OperationDetailRepository;
import kg.easy.finalproject.mappers.OperationDetailMapper;
import kg.easy.finalproject.models.dto.OperationDetailDto;
import kg.easy.finalproject.services.OperationDetailsService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OperationDetailsServiceImpl implements OperationDetailsService {

    @Autowired
    OperationDetailRepository operationDetailRepository;

    @Override
    public void saveOperationDetails(List<OperationDetailDto> operationDetailDtoList) {
        for (OperationDetailDto element: operationDetailDtoList){
            operationDetailRepository.save(OperationDetailMapper.INSTANCE.mapToOperationDetail(element));
        }

    }
}
