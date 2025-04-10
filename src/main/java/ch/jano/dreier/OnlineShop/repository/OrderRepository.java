package ch.jano.dreier.OnlineShop.repository;
import ch.jano.dreier.OnlineShop.entity.OrderEntitity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntitity, Long> {
    List<OrderEntitity> findByUsername(String username);
}
