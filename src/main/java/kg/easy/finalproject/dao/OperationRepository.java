package kg.easy.finalproject.dao;

import kg.easy.finalproject.models.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    Operation findOperationById(Long id);
}
