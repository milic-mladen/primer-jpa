package org.malkier.primerjpa.bootstrap;

import lombok.AllArgsConstructor;
import org.malkier.primerjpa.model.Customer;
import org.malkier.primerjpa.model.Order;
import org.malkier.primerjpa.model.OrderItem;
import org.malkier.primerjpa.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        Customer customer = new Customer();
        customer.setName("Test Customer");

        Order order = new Order();
        order.setDescription("First Order");

        OrderItem item1 = new OrderItem();
        item1.setName("Item 1");
        order.addItem(item1);

        OrderItem item2 = new OrderItem();
        item2.setName("Item 2");
        order.addItem(item2);

        customer.getOrders().add(order);
        order.setCustomer(customer);

        customerRepository.save(customer);

        System.out.println("Inicijalni podaci su uspešno dodati u bazu.");
    }
}

