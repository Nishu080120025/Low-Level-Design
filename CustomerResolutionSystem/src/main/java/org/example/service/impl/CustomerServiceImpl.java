package org.example.service.impl;

import org.example.entities.Customer;
import org.example.entities.Issues;
import org.example.entities.enums.IssueType;
import org.example.entities.enums.ResolutionStatus;
import org.example.repository.CustomerRepositiory;
import org.example.repository.IssueRepository;
import org.example.request.CustomerUpdateRequest;
import org.example.service.CustomerService;
import org.example.util.IdUtil;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private CustomerRepositiory customerRepositiory;
    private IssueRepository issueRepository;
    private IdUtil idUtil;
    public CustomerServiceImpl(CustomerRepositiory customerRepositiory, IssueRepository issueRepository, IdUtil idUtil) {
        this.customerRepositiory = customerRepositiory;
        this.issueRepository = issueRepository;
        this.idUtil = idUtil;
    }

    @Override
    public void addCustomer(String name,String email,String phoneNumber){
        String customerId=idUtil.generateUniqueId();
        Customer customer=new Customer(customerId,name,email,phoneNumber,new ArrayList<>());
        customerRepositiory.addCustomer(customerId,customer);
    }

    @Override
    public void updateCustomer(String customerId, CustomerUpdateRequest updateRequest){
        Customer customer=customerRepositiory.getCustomer(customerId);
        if(updateRequest.getName()!=null){
            customer.setName(updateRequest.getName());
        }
        if(updateRequest.getEmail()!=null){
            customer.setName(updateRequest.getEmail());
        }
        if(updateRequest.getPhoneNumber()!=null){
            customer.setPhoneNumber(updateRequest.getPhoneNumber());
        }
        if(updateRequest.getIssuesList()!=null){
            customer.setIssuesList(updateRequest.getIssuesList());
        }
        Customer updateCustomer=customerRepositiory.updateCustomer(customer);
        System.out.println("Updated Customer :"+customer);
    }

    @Override
    public void deleteCustomer(String customerId){
       customerRepositiory.removeCustomer(customerId);

    }

    @Override
    public void viewCustomerDetails(String customerId){
        Customer customer=customerRepositiory.getCustomer(customerId);
        System.out.println("customer details are : "+customer);
    }

    @Override
    public void createIssue(String transactionId, IssueType issueType, String description, String email){
        String issueId=idUtil.generateUniqueId();
        Customer customer=customerRepositiory.getCustomerByEmail(email);
        Issues issues=new Issues(issueId,transactionId,description,ResolutionStatus.OPEN,issueType, customer.getUserId(), null);
        customer.getIssuesList().add(issues);
        issueRepository.addIssue(issueId,issues);
        System.out.println("Issue created successfully for customer with email :"+customer.getEmail()+"with issueId: "+issueId);
    }

    @Override
    public List<Issues> viewCustomerIssues(String customerId){
        Customer customer=customerRepositiory.getCustomer(customerId);
        return customer.getIssuesList();
    }

    @Override
    public void viewCustomerIssueByIssueId(String issueId,String customerId){
        Customer customer=customerRepositiory.getCustomer(customerId);
        List<Issues>issues=customer.getIssuesList();
        Issues concernedIssue=issues.stream().filter(i->i.getIssueId().equals(issueId)).findFirst().orElse(null);
        System.out.println("Concerned issues is : "+concernedIssue);
    }

    @Override
    public Customer getCustomerById(String customerId){
        return customerRepositiory.getCustomer(customerId);
    }

    @Override
    public Customer getCustomerByEmail(String email){
        return customerRepositiory.getCustomerByEmail(email);
    }
}
