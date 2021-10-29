package kg.easy.finalproject.models.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "requests")

public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    boolean success;
    @CreationTimestamp
    Date addDate;
    @ManyToOne
    @JoinColumn(name = "id_codes")
    Code code;
}
