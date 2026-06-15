package org.example.service.impl;

import org.example.entities.Agent;
import org.example.entities.Issues;
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
        Agent agent = issues.getAssignedAgent();
        if (agent == null) {
            System.out.println("Issue with ID " + issueId + " is not assigned to any agent.");
            return;
        }
        if (agent.getCurrentIssue().getIssueId().equals(issueId)) {
            agent.resolveCurrentIssue();
            issues.setDescription(resolutionDetails);
        } else {
            Issues issue = agent.getWaitingTickets().stream().filter(i -> i.getIssueId().equals(issueId)).findFirst().orElse(null);
            if (issue != null) {
                agent.resolveWaitingIssue(issueId);
                issues.setDescription(resolutionDetails);
            } else {
                System.out.println("Issue with ID " + issueId + " is not assigned to agent " + agent.getName());
                return;
            }
        }
        issueRepository.updateIssue(issues);
        System.out.println("Issue with ID " + issueId + " resolved by agent " + agent.getName());
    }

    @Override
    public List<Issues> viewAssignedIssues(String agentId){
        Agent agent=agentRepository.getAgent(agentId);
        if(agent==null){
            System.out.println("Agent with ID "+agentId+" not found.");
            return null;
        }
        return agent.getAssignedTickets();
    }

}
