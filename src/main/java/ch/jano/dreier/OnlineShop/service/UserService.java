package ch.jano.dreier.OnlineShop.service;

import ch.jano.dreier.OnlineShop.entity.UserEntity;
import ch.jano.dreier.OnlineShop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    public Optional<UserEntity> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<UserEntity> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public UserEntity save(UserEntity user) {
        return repository.save(user);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
