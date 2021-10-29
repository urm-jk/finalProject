package kg.easy.finalproject.models.entities;

import kg.easy.finalproject.enums.CodeStatus;
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
@Table(name = "codes")

public class Code {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String code;
    @CreationTimestamp
    Date startDate;
    Date endDate;
    @Enumerated(EnumType.STRING)
    CodeStatus codeStatus;
    @ManyToOne
    @JoinColumn(name = "id_users")
    User user;
}
