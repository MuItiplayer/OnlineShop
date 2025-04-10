package ch.jano.dreier.OnlineShop.controller;

import ch.jano.dreier.OnlineShop.entity.OrderEntitity;
import ch.jano.dreier.OnlineShop.security.Roles;
import ch.jano.dreier.OnlineShop.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@Validated
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/{username}")
    @RolesAllowed({Roles.USER, Roles.ADMIN})
    public List<OrderEntitity> getOrdersByUser(@PathVariable String username) {
        return service.getOrdersByUser(username);
    }


    @GetMapping
    @RolesAllowed(Roles.ADMIN)
    public List<OrderEntitity> getAllOrders() {
        return service.findAll();
    }

    @PostMapping
    @RolesAllowed(Roles.USER)
    public OrderEntitity createOrder(@RequestBody OrderEntitity orderEntitity) {
        return service.createOrder(orderEntitity);
    }

    @PutMapping("/update/{id}")
    @RolesAllowed(Roles.USER)
    public OrderEntitity updateOrder(@PathVariable Long id, @RequestBody OrderEntitity updatedOrder) {
        OrderEntitity existingOrder = service.findById(id);
        if (existingOrder == null) {
            throw new RuntimeException("Order not found");
        }

        existingOrder.setProductName(updatedOrder.getProductName());
        existingOrder.setQuantity(updatedOrder.getQuantity());
        existingOrder.setUsername(updatedOrder.getUsername());
        return service.createOrder(existingOrder);
    }

    @DeleteMapping("/delete/{id}")
    @RolesAllowed(Roles.USER)
    public void deleteOrder(@PathVariable Long id) {
        service.deleteById(id);
    }
}
