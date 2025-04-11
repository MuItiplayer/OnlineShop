package ch.jano.dreier.OnlineShop.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;

    @ManyToMany(mappedBy = "products")
    private List<OrderEntitity> orders;

    public ProductEntity(Long id, String name, String description, double price, List<OrderEntitity> orders) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderEntitity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntitity> orders) {
        this.orders = orders;
    }
}
