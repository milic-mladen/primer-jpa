package org.malkier.primerjpa.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.malkier.primerjpa.model.Customer;
import org.malkier.primerjpa.model.Order;
import org.malkier.primerjpa.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {


    private final CustomerService customerService;

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
}

