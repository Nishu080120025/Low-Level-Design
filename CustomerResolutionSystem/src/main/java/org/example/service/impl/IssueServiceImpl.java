package org.example.service.impl;

import org.example.entities.Agent;
import org.example.entities.Issues;
import org.example.entities.enums.ResolutionStatus;
import org.example.repository.AgentRepository;
import org.example.repository.IssueRepository;
import org.example.service.IssueService;
import org.example.strategy.AssignmentStrategy;
import org.example.util.IdUtil;

import java.util.List;

public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final IdUtil idUtil;
    private final AssignmentStrategy assignmentStrategy;
    private final AgentRepository agentRepository;
    public IssueServiceImpl(IssueRepository issueRepository, IdUtil idUtil, AssignmentStrategy assignmentStrategy,AgentRepository agentRepository) {
        this.issueRepository = issueRepository;
        this.idUtil = idUtil;
        this.assignmentStrategy = assignmentStrategy;
        this.agentRepository=agentRepository;
    }

    @Override
    public void assignIssue(String issueId){
        Issues issues=issueRepository.getIssueByIssueId(issueId);
        if(issues==null){
            System.out.println("Issue with ID "+issueId+" not found.");
            return;
        }
        Agent assignedAgent=assignmentStrategy.assignAgent(issueId,issues.getIssueType());
        if(assignedAgent==null){
            System.out.println("No available agent found for issue ID "+issueId);
            return;
        }
        issues.setAssignedAgent(assignedAgent.getUserId());
        assignedAgent.addIssue(issueId);
        issueRepository.updateIssue(issues);
        agentRepository.updateAgent(assignedAgent);
        System.out.println("Issue with ID "+issueId+" assigned to agent "+assignedAgent.getName());
    }

    @Override
    public Issues getIssueById(String issueId){
        Issues issues=issueRepository.getIssueByIssueId(issueId);
        if(issues==null){
            System.out.println("Issue with ID "+issueId+" not found.");
            return null;
        }
        return issues;
    }

    @Override
    public List<Issues> getAllIssues(){
        return issueRepository.getAllIssues();
    }

    @Override
    public void updateIssue(String issueId, ResolutionStatus resolutionStatus,String details){
        Issues issues=issueRepository.getIssueByIssueId(issueId);
        if(issues==null){
            System.out.println("Issue with ID "+issueId+" not found.");
            return;
        }
        issues.setResolutionStatus(resolutionStatus);
        issues.setDescription(details);
        issueRepository.updateIssue(issues);
        System.out.println("Issue with ID "+issueId+" updated successfully.");
    }

    @Override
    public Issues getIssueByTransactionId(String transactionId){
        return issueRepository.getAllIssues().stream()
                .filter(issue -> issue.getTransactionId().equals(transactionId))
                .findFirst()
                .orElse(null);
    }

}
