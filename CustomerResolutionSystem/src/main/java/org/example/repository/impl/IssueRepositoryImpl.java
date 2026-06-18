package org.example.repository.impl;

import org.example.entities.Issues;
import org.example.entities.enums.IssueType;
import org.example.entities.enums.ResolutionStatus;
import org.example.repository.IssueRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IssueRepositoryImpl implements IssueRepository {
    private Map<String, Issues> issueMap;
    public IssueRepositoryImpl(Map<String, Issues> issueMap) {
        this.issueMap = issueMap;
    }
    @Override
    public void addIssue(String issueId, Issues issues) {
        issueMap.put(issueId, issues);
        System.out.println("Issue added successfully");
    }

    @Override
    public Issues removeIssue(String issueId) {
        if (issueMap.containsKey(issueId)) {
            Issues removedIssue = issueMap.remove(issueId);
            System.out.println("Issue removed successfully");
            return removedIssue;
        } else {
            System.out.println("Issue not found");
            return null;
        }
    }

    @Override
    public Issues updateIssue(Issues issues) {
        if (issueMap.containsKey(issues.getIssueId())) {
            issueMap.put(issues.getIssueId(), issues);
            System.out.println("Issue updated successfully");
            return issues;
        } else {
            System.out.println("Issue not found");
            return null;
        }
    }

    @Override
    public Issues getIssueByIssueId(String issueId) {
        return issueMap.getOrDefault(issueId, null);
    }

    @Override
    public List<Issues> getAllIssues() {
        return new ArrayList<>(issueMap.values());
    }

    @Override
    public List<Issues> getAllIssuesByCustomerId(String customerId) {
        List<Issues> issuesByCustomerId = new ArrayList<>();
        for (Issues issue : issueMap.values()) {
            if (issue.getCustomerId().equals(customerId)) {
                issuesByCustomerId.add(issue);
            }
        }
        return issuesByCustomerId;
    }

    @Override
    public List<Issues> getAllIssuesByResolutionStatus(ResolutionStatus resolutionStatus) {
        List<Issues> issuesByResolutionStatus = new ArrayList<>();
        for (Issues issue : issueMap.values()) {
            if (issue.getResolutionStatus() == resolutionStatus) {
                issuesByResolutionStatus.add(issue);
            }
        }
        return issuesByResolutionStatus;
    }

    @Override
    public List<Issues> getAllIssuesByIssueType(IssueType issueType) {
        List<Issues> issuesByIssueType = new ArrayList<>();
        for (Issues issue : issueMap.values()) {
            if (issue.getIssueType() == issueType) {
                issuesByIssueType.add(issue);
            }
        }
        return issuesByIssueType;
    }


    @Override
    public List<Issues>getAllIssuesByCustomerEmail(String email){

        List<Issues>issuesByCustomerEmail=new ArrayList<>();
        for(Issues issue:issueMap.values()){
            if(issue.getCustomerId().equals(email)){
                issuesByCustomerEmail.add(issue);
            }
        }
        return issuesByCustomerEmail;
    }


}
