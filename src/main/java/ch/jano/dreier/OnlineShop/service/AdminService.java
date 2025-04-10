package ch.jano.dreier.OnlineShop.service;

import ch.jano.dreier.OnlineShop.entity.AdminEntity;
import ch.jano.dreier.OnlineShop.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository repository;

    public AdminService(AdminRepository repository) {
        this.repository = repository;
    }

    public List<AdminEntity> findAll() {
        return repository.findAll();
    }

    public Optional<AdminEntity> findById(Long id) {
        return repository.findById(id);
    }

    public AdminEntity save(AdminEntity admin) {
        return repository.save(admin);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
