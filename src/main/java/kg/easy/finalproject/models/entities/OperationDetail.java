package kg.easy.finalproject.models.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "operationsDetails")

public class OperationDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    double amount;
    double price;
    @ManyToOne
    @JoinColumn(name = "id_products")
    Product product;
    @ManyToOne
    @JoinColumn(name = "id_operations")
    Operation operation;
}
