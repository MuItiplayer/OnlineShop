package ch.jano.dreier.OnlineShop.controller;

import ch.jano.dreier.OnlineShop.entity.ProductEntity;
import ch.jano.dreier.OnlineShop.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ch.jano.dreier.OnlineShop.security.Roles;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@Validated
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/read")
    @RolesAllowed(Roles.USER)
    public List<ProductEntity> getAllProducts() {
        return service.findAll();
    }


    @GetMapping("/read/{id}")
    @RolesAllowed(Roles.USER)
    public ProductEntity getProductById(@PathVariable Long id) {
        ProductEntity product = service.findById(id);
        if (product == null) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        return product;
    }


    @PostMapping("/create")
    @RolesAllowed(Roles.ADMIN)
    public ProductEntity createProduct(@RequestBody ProductEntity product) {
        return service.save(product);
    }

    @PutMapping("/update/{id}")
    @RolesAllowed(Roles.ADMIN)
    public ProductEntity updateProduct(@PathVariable Long id, @RequestBody ProductEntity productDetails) {
        ProductEntity existingProduct = service.findById(id);
        if (existingProduct == null) {
            throw new RuntimeException("Product not found");
        }

        existingProduct.setName(productDetails.getName());
        existingProduct.setDescription(productDetails.getDescription());
        existingProduct.setPrice(productDetails.getPrice());

        return service.save(existingProduct);
    }

    @DeleteMapping("/delete/{id}")
    @RolesAllowed(Roles.ADMIN)
    public void deleteProduct(@PathVariable Long id) {
        service.deleteById(id);
    }
}
