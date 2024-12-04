package org.malkier.primerjpa.repository;


import org.malkier.primerjpa.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
