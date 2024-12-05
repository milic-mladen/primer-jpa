package org.malkier.primerjpa.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.malkier.primerjpa.model.Customer;
import org.malkier.primerjpa.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    @PersistenceContext
    private EntityManager entityManager;
    private final CustomerRepository customerRepository;
//    public Customer getCustomerById(Long id) {
//        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
//    }


    //pokazi sa i bez transactional anotacije(bez ce isto bacati exception)
    //da li bi radilo i bez transactionala?
    @Transactional
    public Customer getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));


        // Eksplicitno učitavanje lazy polja
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
    @Transactional
    public void demonstrateDetachedIssue() {
        // Učitaj objekat iz baze (Persistent state)
        Customer customer = customerRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Odvajanje objekta (Detached state)
        entityManager.detach(customer);

        try {
            // Pokušaj da se izvrši operacija nad detached objektom
            customer.setName("Updated Name");
            customerRepository.save(customer); // Ovo izaziva grešku
        } catch (Exception e) {
            System.out.println("Exception with detached object: " + e.getMessage());
        }
    }
    @Transactional
    public void fixDetachedIssue() {
        // Učitaj objekat iz baze (Persistent state)
        Customer customer = customerRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Odvajanje objekta (Detached state)
        entityManager.detach(customer);

        // Rešenje: merge objekta
        Customer mergedCustomer = entityManager.merge(customer);
        mergedCustomer.setName("Updated Name");
        System.out.println("Fixed detached issue with merge: " + mergedCustomer);
    }
}
