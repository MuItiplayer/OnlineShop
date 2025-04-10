package ch.jano.dreier.OnlineShop.repository;
import ch.jano.dreier.OnlineShop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUsername(String username);
}
