package ch.jano.dreier.OnlineShop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@Table(name = "admins")
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private boolean active;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<ProductEntity> products;
}
