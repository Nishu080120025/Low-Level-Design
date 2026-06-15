package org.example.entities;

import org.example.entities.enums.UserRole;

import java.util.List;

public class Customer extends Users {
    private String customerId;
    private String name;
    private String email;
    private String phoneNumber;
    private List<Issues> issuesList;
    public Customer(String customerId, String name, String email, String phoneNumber,List<Issues>issueList) {
        super(customerId, name, UserRole.CUSTOMER, email, phoneNumber);
        this.issuesList=issueList;
    }
    public List<Issues> getIssuesList() {
        return issuesList;
    }
    public void setIssuesList(List<Issues> issuesList) {
        this.issuesList = issuesList;
    }

    @Override
    public String toString(){
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", issuesList=" + issuesList +
                '}';
    }
}
