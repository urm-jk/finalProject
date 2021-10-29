package kg.easy.finalproject.services.impl;

import kg.easy.finalproject.dao.RequestRepository;
import kg.easy.finalproject.mappers.CodeMapper;
import kg.easy.finalproject.models.dto.CodeDto;
import kg.easy.finalproject.models.entities.Request;
import kg.easy.finalproject.services.RequestService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestServiceImpl implements RequestService {
    @Autowired
    RequestRepository requestRepository;
    @Override
    public void saveRequest(CodeDto checkUserCode, boolean value) {

        Request saveRequest = new Request();
        saveRequest.setCode(CodeMapper.INSTANCE.mapToCode(checkUserCode));
        saveRequest.setSuccess(value);
        requestRepository.save(saveRequest);
    }

    @Override
    public int countFailedAttempts(CodeDto codeDto) {

        return requestRepository.countByCodeAndSuccess(CodeMapper.INSTANCE.mapToCode(codeDto), false);
    }
}
