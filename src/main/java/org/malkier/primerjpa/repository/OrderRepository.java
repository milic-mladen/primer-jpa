package org.malkier.primerjpa.repository;

import org.malkier.primerjpa.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
