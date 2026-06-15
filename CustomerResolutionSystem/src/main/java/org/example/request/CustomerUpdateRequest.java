package org.example.request;

import org.example.entities.Issues;

import java.util.List;

public class CustomerUpdateRequest {
    private String name;
    private String email;
    private String phoneNumber;
    private List<Issues> issuesList;

    public CustomerUpdateRequest(String name,String email,String phoneNumber,List<Issues>issuesList){
        this.email=email;
        this.name=name;
        this.phoneNumber=phoneNumber;
        this.issuesList=issuesList;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Issues> getIssuesList() {
        return issuesList;
    }
}
