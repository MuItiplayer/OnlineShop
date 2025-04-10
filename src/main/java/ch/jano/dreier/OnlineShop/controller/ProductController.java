package ch.jano.dreier.OnlineShop.controller;

import ch.jano.dreier.OnlineShop.entity.ProductEntity;
import ch.jano.dreier.OnlineShop.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<ProductEntity> getAllProducts() {
        return service.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ProductEntity createProduct(@RequestBody ProductEntity product) {
        return service.save(product);
    }
}
