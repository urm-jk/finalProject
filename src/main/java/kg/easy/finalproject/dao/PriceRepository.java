package kg.easy.finalproject.dao;

import kg.easy.finalproject.models.entities.Price;
import kg.easy.finalproject.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    List<Price> findAllByProduct (Product product);
    @Query(value = "select * from discunts where product_id=?1 and current_timestamp > start_date and current_timestamp < end_date", nativeQuery = true)
    Price findActualPrice (Product product);

}
