package org.example.strategy.impl;

import org.example.entities.Agent;
import org.example.entities.enums.IssueType;
import org.example.repository.AgentRepository;
import org.example.strategy.AssignmentStrategy;

import java.util.List;

public class RoundRobinAssignmentStrategy implements AssignmentStrategy {
    private AgentRepository agentRepository;
    private static int currentIndex = 0;

    public RoundRobinAssignmentStrategy(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Override
    public Agent assignAgent(String issueId, IssueType issueType) {
        List<Agent> agentList = agentRepository.getAllAgents();
        for (int i = 0; i < agentList.size(); i++) {
            int idx = (currentIndex + i) % agentList.size();
            Agent agent = agentList.get(idx);
            if (agent.isAvailable() && agent.getExpertise().contains(issueType)) {
                currentIndex = (idx + 1) % agentList.size();
                return agent;
            }
        }
        return null;
    }
}
