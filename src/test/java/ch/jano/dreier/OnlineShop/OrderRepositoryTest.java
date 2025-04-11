package ch.jano.dreier.OnlineShop;

import ch.jano.dreier.OnlineShop.entity.OrderEntitity;
import ch.jano.dreier.OnlineShop.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @DisplayName("Speichert Order und findet sie per Username")
    void testFindByUsername() {
        OrderEntitity order1 = new OrderEntitity();
        order1.setUsername("jano1");

        OrderEntitity order2 = new OrderEntitity();
        order2.setUsername("jano1");

        OrderEntitity order3 = new OrderEntitity();
        order3.setUsername("jano3");

        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);

        List<OrderEntitity> shawtyOrders = orderRepository.findByUsername("jano1");

        assertThat(shawtyOrders).hasSize(2);
        assertThat(shawtyOrders).allMatch(order -> order.getUsername().equals("jano1"));
    }
}