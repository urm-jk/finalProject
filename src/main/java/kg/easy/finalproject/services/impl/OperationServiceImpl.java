package kg.easy.finalproject.services.impl;

import kg.easy.finalproject.models.dto.InputDataForOperation;
import kg.easy.finalproject.services.OperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OperationServiceImpl implements OperationService {
    @Override
    public ResponseEntity<?> provideOperation(String token, List<InputDataForOperation> sellingList) {
        return null;
    }

    @Override
    public ResponseEntity<?> payment(String token, Long operationId, double cash) {
        return null;
    }
}
