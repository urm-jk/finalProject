package kg.easy.finalproject.dao;

import kg.easy.finalproject.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByNameOrBarcode (String name, String barcode);

    Product findByBarcode(String barcode);
}
