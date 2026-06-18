package org.example.service.impl;

import org.example.entities.Agent;
import org.example.entities.Issues;
import org.example.entities.enums.ResolutionStatus;
import org.example.repository.AgentRepository;
import org.example.repository.IssueRepository;
import org.example.service.AgentService;
import org.example.util.IdUtil;

import java.util.List;

public class AgentServiceImpl implements AgentService {

    private final AgentRepository agentRepository;
    private final IssueRepository issueRepository;
    private final IdUtil idUtil;

    public AgentServiceImpl(AgentRepository agentRepository,IssueRepository issueRepository,IdUtil idUtil){
        this.agentRepository=agentRepository;
        this.issueRepository=issueRepository;
        this.idUtil=idUtil;
    }

    @Override
    public void resolveIssue(String issueId,String resolutionDetails) {
        Issues issues = issueRepository.getIssueByIssueId(issueId);
        if (issues == null) {
            System.out.println("Issue with ID " + issueId + " not found.");
            return;
        }
        String agentId = issues.getAssignedAgentId();
        if (agentId == null) {
            System.out.println("Issue with ID " + issueId + " is not assigned to any agent.");
            return;
        }
        Agent agent = agentRepository.getAgent(agentId);
        if (agent == null) {
            System.out.println("Agent with ID " + agentId + " not found.");
            return;
        }
        issues.setResolutionStatus(ResolutionStatus.RESOLVED);
        issues.setDescription(resolutionDetails);
        if(agent.getCurrentIssue().equals(issueId)){
            agent.promoteWaitingIssueToCurrent();
        }
        else if(agent.getWaitingTickets().contains(issueId)){
            agent.removeWaitingIssue(issueId);
        }
        agentRepository.updateAgent(agent);
        issueRepository.updateIssue(issues);
        System.out.println("Issue with ID " + issueId + " resolved by agent " + agent.getName());
    }

    @Override
    public List<String> viewAssignedIssues(String agentId){
        Agent agent=agentRepository.getAgent(agentId);
        if(agent==null){
            System.out.println("Agent with ID "+agentId+" not found.");
            return null;
        }
        return agent.getAssignedTickets();
    }

}
