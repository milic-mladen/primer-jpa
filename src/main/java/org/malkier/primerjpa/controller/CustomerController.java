package org.malkier.primerjpa.controller;

import lombok.RequiredArgsConstructor;
import org.malkier.primerjpa.model.Customer;
import org.malkier.primerjpa.model.Order;
import org.malkier.primerjpa.model.OrderItem;
import org.malkier.primerjpa.service.CustomerService;
import org.malkier.primerjpa.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {


    private final CustomerService customerService;
    private final OrderService orderService;

    @GetMapping("/get-all-customers")
    public List<Customer> getCustomerOrders() {
        List<Customer> customers = customerService.getAllCustomers();
        return customers;
    }

    @GetMapping("/{id}/orders")
    public List<Order> getCustomerOrders(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        return customer.getOrders();
    }
    @GetMapping("/lifecycle")
    public void demonstrateLifecycle() {
        customerService.demonstrateLifecycle();
    }

    @GetMapping("/detached-issue")
    public void demonstrateDetachedIssue() {
        customerService.demonstrateDetachedIssue();
    }

    @GetMapping("/detached-fix")
    public void fixDetachedIssue() {
        customerService.fixDetachedIssue();
    }
    @PostMapping("/{orderId}/items")
    public void addItemToOrder(@PathVariable Long orderId, @RequestBody OrderItem newItem) {
        orderService.addItemToOrder(orderId, newItem);
    }

}

