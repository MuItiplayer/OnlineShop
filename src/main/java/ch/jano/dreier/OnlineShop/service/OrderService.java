package ch.jano.dreier.OnlineShop.service;

import ch.jano.dreier.OnlineShop.entity.OrderEntitity;
import ch.jano.dreier.OnlineShop.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService {

    private final OrderRepository repo;

    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }

    public List<OrderEntitity> findAll() {
        return repo.findAll();
    }

    public List<OrderEntitity> getOrdersByUser(String username) {
        return repo.findByUsername(username);
    }

    public OrderEntitity findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public OrderEntitity createOrder(OrderEntitity orderEntitity) {
        return repo.save(orderEntitity);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
