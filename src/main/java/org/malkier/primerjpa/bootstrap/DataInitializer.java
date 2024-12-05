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
        // Kreiraj inicijalnog kupca
        Customer customer = new Customer();
        customer.setName("Test Customer");

        // Kreiraj narudžbinu
        Order order = new Order();
        order.setDescription("First Order");

        // Dodaj stavke narudžbine
        OrderItem item1 = new OrderItem();
        item1.setName("Item 1");
        order.addItem(item1);

        OrderItem item2 = new OrderItem();
        item2.setName("Item 2");
        order.addItem(item2);

        // Poveži narudžbinu sa kupcem
        customer.getOrders().add(order);
order.setCustomer(customer);
        // Sačuvaj kupca (kaskadno će sačuvati i narudžbine i stavke)
        customerRepository.save(customer);

        System.out.println("Inicijalni podaci su uspešno dodati u bazu.");
    }
}

