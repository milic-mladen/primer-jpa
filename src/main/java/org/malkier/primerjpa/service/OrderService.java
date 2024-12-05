package org.malkier.primerjpa.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.malkier.primerjpa.model.Order;
import org.malkier.primerjpa.model.OrderItem;
import org.malkier.primerjpa.repository.OrderRepository;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;


    @Transactional
    public void removeItemFromOrder(Long orderId, Long itemId) {
        // Pronalazi Order iz baze
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Pronalazi OrderItem iz Order-a
        OrderItem itemToRemove = order.getItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item not found"));

        // Uklanja OrderItem iz Order-a
        order.removeItem(itemToRemove);

        // Čuva promene
        orderRepository.save(order);
    }
    @Transactional
    public void addItemToOrder(Long orderId, OrderItem newItem) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.addItem(newItem);
        orderRepository.save(order);
    }
}
