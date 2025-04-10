package ch.jano.dreier.OnlineShop.controller;
import ch.jano.dreier.OnlineShop.entity.Order;
import ch.jano.dreier.OnlineShop.service.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Order> getOrdersByUser(@PathVariable String username) {
        return service.getOrdersByUser(username);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Order createOrder(@RequestBody Order order) {
        return service.createOrder(order);
    }
}
