package org.example.repository;

import org.example.entities.Issues;
import org.example.entities.enums.IssueType;
import org.example.entities.enums.ResolutionStatus;

import java.util.List;

public interface IssueRepository {

    void addIssue(String issueId, Issues issue);

    Issues removeIssue(String issueId);

    Issues updateIssue(Issues issues);

    Issues getIssueByIssueId(String issueId);

    List<Issues> getAllIssues();

    List<Issues> getAllIssuesByCustomerId(String customerId);

    List<Issues> getAllIssuesByResolutionStatus(ResolutionStatus resolutionStatus);

    List<Issues> getAllIssuesByIssueType(IssueType issueType);

    List<Issues>getAllIssuesByCustomerEmail(String email);

}
