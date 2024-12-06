package org.malkier.primerjpa.repository;


import org.malkier.primerjpa.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query(value = "SELECT * FROM customer" , nativeQuery = true)
    List<Customer>findCustomers();
}
