package kg.easy.finalproject.services;

import kg.easy.finalproject.models.dto.CodeDto;

public interface RequestService {
    void saveRequest(CodeDto codeDto, boolean value);

    int countFailedAttempts(CodeDto codeDto);
}
