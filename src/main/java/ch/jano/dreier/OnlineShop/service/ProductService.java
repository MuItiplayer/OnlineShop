package ch.jano.dreier.OnlineShop.service;

import ch.jano.dreier.OnlineShop.entity.Product;
import ch.jano.dreier.OnlineShop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> findAll() {
        return repo.findAll();
    }

    public Product save(Product product) {
        return repo.save(product);
    }
}
