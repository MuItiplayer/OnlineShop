package ch.jano.dreier.OnlineShop.repository;

import ch.jano.dreier.OnlineShop.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
}
