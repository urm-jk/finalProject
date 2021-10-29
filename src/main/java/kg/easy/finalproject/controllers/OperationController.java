package kg.easy.finalproject.controllers;

import kg.easy.finalproject.models.dto.InputDataForOperation;
import kg.easy.finalproject.services.OperationService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/operations")
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class OperationController {
    @Autowired
    OperationService operationService;

    @PostMapping("/provideOperation")
    public ResponseEntity<?> provideOperation(@RequestHeader String token, @RequestBody List<InputDataForOperation> sellingList){
        return operationService.provideOperation(token, sellingList);
    }

    @GetMapping("/payment")
    public ResponseEntity<?> payment(@RequestHeader String token, @RequestParam Long operationId, @RequestParam double cash){
        return operationService.payment(token, operationId, cash);
    }
}
