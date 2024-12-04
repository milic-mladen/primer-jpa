package org.malkier.primerjpa.controller;

import org.malkier.primerjpa.model.Customer;
import org.malkier.primerjpa.model.Order;
import org.malkier.primerjpa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}/orders")
    public List<Order> getCustomerOrders(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        // Ovo izaziva LazyInitializationException
        return customer.getOrders();
    }
}

