package org.malkier.primerjpa.repository;


import org.malkier.primerjpa.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @Query(value = "SELECT * FROM customer" , nativeQuery = true)
    List<Customer>pronadjiKupce();
}
