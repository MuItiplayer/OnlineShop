package ch.jano.dreier.OnlineShop.service;

import ch.jano.dreier.OnlineShop.entity.ProductEntity;
import ch.jano.dreier.OnlineShop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<ProductEntity> findAll() {
        return repo.findAll();
    }

    public ProductEntity save(ProductEntity product) {
        return repo.save(product);
    }
}
