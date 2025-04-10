package ch.jano.dreier.OnlineShop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class OrderEntitity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private int quantity;

    private String username;

    private LocalDateTime orderDate = LocalDateTime.now();

    @ManyToMany
    private List<ProductEntity> product;
}
