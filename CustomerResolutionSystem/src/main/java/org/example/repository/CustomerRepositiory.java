package org.example.repository;

import org.example.entities.Customer;

import java.util.List;

public interface CustomerRepositiory {

    void addCustomer(String customerId, Customer customer);

    void removeCustomer(String customerId);

    Customer updateCustomer(Customer customer);

    Customer getCustomer(String customerId);

    Customer getCustomerByEmail(String customerEmail);

    List<Customer> getAllCustomers();
}
