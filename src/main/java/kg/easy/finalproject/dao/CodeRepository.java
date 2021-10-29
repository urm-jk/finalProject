package kg.easy.finalproject.dao;

import kg.easy.finalproject.enums.CodeStatus;
import kg.easy.finalproject.models.entities.Code;
import kg.easy.finalproject.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {
    Code findByUserAndCodeStatus(User user, CodeStatus codeStatus);
}
