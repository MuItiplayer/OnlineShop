package ch.jano.dreier.OnlineShop.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ch.jano.dreier.OnlineShop.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}