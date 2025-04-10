package ch.jano.dreier.OnlineShop.controller;
import ch.jano.dreier.OnlineShop.entity.OrderEntitity;
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
    public List<OrderEntitity> getOrdersByUser(@PathVariable String username) {
        return service.getOrdersByUser(username);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public OrderEntitity createOrder(@RequestBody OrderEntitity orderEntitity) {
        return service.createOrder(orderEntitity);
    }
}
