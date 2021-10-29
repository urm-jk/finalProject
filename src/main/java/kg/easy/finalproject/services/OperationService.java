package kg.easy.finalproject.services;

import kg.easy.finalproject.models.dto.InputDataForOperation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OperationService {
    ResponseEntity<?>provideOperation(String token, List<InputDataForOperation> sellingList);

    ResponseEntity<?>payment(String token, Long operationId, double cash);
}
