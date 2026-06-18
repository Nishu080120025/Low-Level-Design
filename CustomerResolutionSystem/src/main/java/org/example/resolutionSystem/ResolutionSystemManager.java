package org.example.resolutionSystem;

import org.example.entities.Agent;
import org.example.entities.Issues;
import org.example.entities.enums.IssueType;
import org.example.entities.enums.ResolutionStatus;
import org.example.repository.AgentRepository;
import org.example.repository.CustomerRepositiory;
import org.example.repository.IssueRepository;
import org.example.service.AdminService;
import org.example.service.AgentService;
import org.example.service.CustomerService;
import org.example.service.IssueService;
import org.example.util.IdUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ResolutionSystemManager {

    private final AgentService agentService;
    private final IssueService issueService;
    private final AdminService adminService;
    private final CustomerService customerService;
    private final IdUtil idUtil;


    public ResolutionSystemManager(AgentService agentService, IssueService issueService, AdminService adminService, CustomerService customerService, IdUtil idUtil) {
        this.agentService = agentService;
        this.issueService = issueService;
        this.adminService = adminService;
        this.customerService = customerService;
        this.idUtil = idUtil;
    }

    public void createIssue(String transactionId, IssueType issueType, String description, String email) {
        customerService.createIssue(transactionId, issueType, description, email);
    }

    public void addAgent(String name, String email, List<IssueType> expertise) {
        Agent agentAdded = adminService.addAgent(email, name, expertise);
        if (agentAdded == null) {
            System.out.println("Failed to add agent.");
            return;
        }
        System.out.println("Agent added successfully: " + agentAdded);
    }

    public void updateIssue(String issueId, ResolutionStatus resolutionStatus, String resolutionDetails) {
        issueService.updateIssue(issueId, resolutionStatus, resolutionDetails);
    }

    public void assignIssue(String issueId) {
        issueService.assignIssue(issueId);
    }

    public void viewIssueDetails(String issueId) {
        issueService.getIssueById(issueId);
    }

    public void viewAllIssues() {
        List issuesList = issueService.getAllIssues();
        System.out.println("All Issues: " + issuesList);
    }

    public void viewAgentsWorkHistory() {
        adminService.viewAgentsWorkHistory();
        for (String agentId : adminService.viewAgentsWorkHistory().keySet()) {
            List<String> issueIds = adminService.viewAgentsWorkHistory().get(agentId);
            System.out.println("Agent ID: " + agentId + " has worked on issues: " + issueIds);
        }
    }


    public void resolveIssue(String issueId, String resolutionDetails) {
        agentService.resolveIssue(issueId, resolutionDetails);
    }


    public List<Issues> getIssuesByFilter(Map<String, String> filter) {
        String targetCustomerId = null;
        List<Issues> allIssues = issueService.getAllIssues();
        if (filter.containsKey("email")) {
            String email = filter.get("email");
            if (customerService.getCustomerByEmail(email) == null) {
                System.out.println("customer with email not found " + email);
                return Collections.emptyList();
            }
            targetCustomerId = customerService.getCustomerByEmail(email).getUserId();
        }

        String finalTargetId = targetCustomerId;
        return allIssues.stream().filter(issues -> {
            boolean matches = true;
            if (filter.containsKey("customerId")) {
                matches = matches && (issues.getCustomerId().equals(filter.get("customerId")));
            }
            if (filter.containsKey("resolutionStatus")) {
                matches = matches && (issues.getResolutionStatus().name().equals(filter.get("resolutionStatus")));
            }
            if(finalTargetId!=null){
                matches=matches&&(issues.getCustomerId().equals(finalTargetId));
            }
            if (filter.containsKey("issueType")) {
                matches = matches && (issues.getIssueType().name().equals(filter.get("issueType")));
            }
            if (filter.containsKey("agentId")) {
                matches = matches && (issues.getAssignedAgentId() != null && issues.getAssignedAgentId().equals(filter.get("agentId")));
            }

            return matches;
        }).toList();

    }

    public Issues viewIssuesByTransactionId(String transactionId){
        Issues issues=issueService.getIssueByTransactionId(transactionId);
        if(issues==null){
            System.out.println("No issue found for transaction ID: "+transactionId);
            return null;
        }
        return issues;
    }
}
