package org.malkier.primerjpa.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.malkier.primerjpa.model.Customer;
import org.malkier.primerjpa.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    @PersistenceContext
    private EntityManager entityManager;
    private final CustomerRepository customerRepository;
//    public Customer getCustomerById(Long id) {
//        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
//    }

    @Transactional
    public List<Customer>getAllCustomers(){
        List<Customer>customers= customerRepository.findCustomers();
        customers.forEach(customer -> customer.getOrders().size());
        return customers;
    }


    //da li bi radilo i bez transactionala?
    @Transactional
    public Customer getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));



        customer.getOrders().size(); // Osigurava da se 'orders' učita pre zatvaranja Session-a

        return customer;
    }
    @Transactional
    public void demonstrateLifecycle() {

        // Transient objekat (nije povezan sa bazom)
        Customer transientCustomer = new Customer();
        transientCustomer.setName("Transient Customer");
        System.out.println("Transient state: " + transientCustomer);

        // Persist objekat (Hibernate prati promene i povezan je sa bazom)
        customerRepository.save(transientCustomer);
        System.out.println("Persistent state: " + transientCustomer);

        // Detached objekat (više nije praćen od strane Hibernate-a)
        Long id = transientCustomer.getId();
        System.out.println("Detached state (before find): " + transientCustomer);

        // Ponovno učitavanje (objekat se vraća u persistent state)
        Customer persistentCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        System.out.println("Persistent state (after find): " + persistentCustomer);
    }
    public void demonstrateDetachedIssue() {
        Customer customer = customerRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Customer not found"));


        entityManager.detach(customer);
        try {
            customer.setName("Updated Name");
            customerRepository.save(customer);
        } catch (Exception e) {
            System.out.println("Exception with detached object: " + e.getMessage());
        }
    }
    @Transactional
    public void fixDetachedIssue() {
        Customer customer = customerRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        entityManager.detach(customer);

        Customer mergedCustomer = entityManager.merge(customer);
        mergedCustomer.setName("Updated Name");
        System.out.println("Fixed detached issue with merge: " + mergedCustomer);
    }
}
