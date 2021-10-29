package kg.easy.finalproject.dao;

import kg.easy.finalproject.models.entities.OperationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationDetailRepository extends JpaRepository<OperationDetail, Long> {
}
