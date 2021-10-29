package kg.easy.finalproject.dao;

import kg.easy.finalproject.models.entities.Code;
import kg.easy.finalproject.models.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    int countByCodeAndSuccess(Code code, boolean success);
}
