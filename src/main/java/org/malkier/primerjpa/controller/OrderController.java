package org.malkier.primerjpa.controller;

import lombok.RequiredArgsConstructor;
import org.malkier.primerjpa.model.OrderItem;
import org.malkier.primerjpa.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @DeleteMapping("/{orderId}/items/{itemId}")
    public void removeItemFromOrder(@PathVariable Long orderId, @PathVariable Long itemId) {
        orderService.removeItemFromOrder(orderId, itemId);

    }
    @PostMapping("/{orderId}/items")
    public void addItemToOrder(@PathVariable Long orderId, @RequestBody OrderItem newItem) {
        orderService.addItemToOrder(orderId, newItem);
    }
}
