package org.example.repository.impl;

import org.example.entities.Customer;
import org.example.repository.CustomerRepositiory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepositiory {
    private HashMap<String, Customer> customerMap;

    public CustomerRepositoryImpl(HashMap<String, Customer> customerMap) {
        this.customerMap = customerMap;
    }

    @Override
    public void addCustomer(String customerId, Customer customer) {
        customerMap.put(customerId, customer);
        System.out.println("Customer added successfully");
    }

    @Override
    public void removeCustomer(String customerId) {
        if (customerMap.containsKey(customerId)) {
            customerMap.remove(customerId);
            System.out.println("Customer removed successfully");
        } else {
            System.out.println("Customer not found");
        }
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        if (customerMap.containsKey(customer.getUserId())) {
            customerMap.put(customer.getUserId(), customer);
            System.out.println("Customer updated successfully");
            return customer;
        } else {
            System.out.println("Customer not found");
            return null;
        }
    }

    @Override
    public Customer getCustomer(String customerId) {
        return customerMap.getOrDefault(customerId, null);
    }

    @Override
    public Customer getCustomerByEmail(String email){
        List<Customer>customerList=getAllCustomers();
       Customer customerFetched= customerList.stream().filter(customer -> customer.getEmail().equals(email)).findFirst().orElse(null);
       return customerFetched;
    }

    @Override
    public List<Customer> getAllCustomers(){
        List<Customer>customerList=new ArrayList<>(customerMap.values());
        return customerList;
    }
}
