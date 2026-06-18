package org.example.service;

import org.example.entities.Issues;
import org.example.entities.enums.ResolutionStatus;

import java.util.List;

public interface IssueService {

    void assignIssue(String issueId);

    Issues getIssueById(String issueId);

    List<Issues> getAllIssues();

    void updateIssue(String issueId, ResolutionStatus resolutionStatus, String description);

    Issues getIssueByTransactionId(String transactionId);
}
