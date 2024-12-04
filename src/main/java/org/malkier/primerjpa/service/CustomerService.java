package org.malkier.primerjpa.service;

import lombok.AllArgsConstructor;
import org.malkier.primerjpa.model.Customer;
import org.malkier.primerjpa.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }
}
