package org.example.entities;

import org.example.entities.enums.IssueType;
import org.example.entities.enums.ResolutionStatus;

public class Issues {
    private String issueId;
    private String transactionId;
    private String description;
    private ResolutionStatus resolutionStatus;
    private IssueType issueType;
    private String  assignedAgentId;
    private String customerId;

    public Issues(String issueId, String transactionId, String description, ResolutionStatus resolutionStatus, IssueType issueType, String customerId, String  assignedAgentId) {
        this.issueId = issueId;
        this.transactionId = transactionId;
        this.description = description;
        this.resolutionStatus = resolutionStatus;
        this.issueType = issueType;
        this.customerId = customerId;
        this.assignedAgentId = assignedAgentId;
    }

    public String getIssueId() {
        return this.issueId;
    }

    public String getDescription() {
        return this.description;
    }

    public ResolutionStatus getResolutionStatus() {
        return this.resolutionStatus;
    }

    public IssueType getIssueType() {
        return this.issueType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setResolutionStatus(ResolutionStatus resolutionStatus) {
        this.resolutionStatus = resolutionStatus;
    }

    public void setIssueType(IssueType issueType) {
        this.issueType = issueType;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getAssignedAgentId() {
        return this.assignedAgentId;
    }

    public void setAssignedAgent(String assignedAgentId) {
        this.assignedAgentId = assignedAgentId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    @Override
    public String toString() {
        return "Issues{" +
                "issueId='" + issueId + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", description='" + description + '\'' +
                ", resolutionStatus=" + resolutionStatus +
                ", issueType=" + issueType +
                ", customerId='" + customerId + '\'' +
                ", assignedAgent=" + (assignedAgentId != null ? assignedAgentId: "null") +
                '}';
    }


}
