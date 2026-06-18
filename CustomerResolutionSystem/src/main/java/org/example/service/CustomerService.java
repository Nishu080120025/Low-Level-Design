package org.example.service;

import org.example.entities.Customer;
import org.example.entities.Issues;
import org.example.entities.enums.IssueType;
import org.example.request.CustomerUpdateRequest;

import java.util.List;

public interface CustomerService {

    void addCustomer(String name, String email, String phoneNumber);

    void updateCustomer(String customerId, CustomerUpdateRequest updateRequest);

    void deleteCustomer(String customerId);

    void viewCustomerDetails(String customerId);

    void createIssue(String transactionId, IssueType issueType, String description, String email);

    List<Issues> viewCustomerIssues(String customerId);

    void viewCustomerIssueByIssueId(String issueId, String userId);

    Customer getCustomerById(String customerId);

    Customer getCustomerByEmail(String email);


}
