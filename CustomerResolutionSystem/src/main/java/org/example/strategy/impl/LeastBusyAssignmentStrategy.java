package org.example.strategy.impl;

import org.example.entities.Agent;
import org.example.entities.enums.IssueType;
import org.example.repository.AgentRepository;
import org.example.strategy.AssignmentStrategy;

public class LeastBusyAssignmentStrategy implements AssignmentStrategy {
    private final AgentRepository agentRepository;

    public LeastBusyAssignmentStrategy(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Override
    public Agent assignAgent(String issueId, IssueType issueType) {
        Agent leastBusyAgent = null;
        int minLoad = Integer.MAX_VALUE;
        for (Agent agent : agentRepository.getAllAgents()) {
            if (agent.isAvailable() && agent.getExpertise().contains(issueType)) {
                int load = agent.getWaitingTickets().size();
                if (load < minLoad) {
                    minLoad = load;
                    leastBusyAgent = agent;
                }
            }
        }
        return leastBusyAgent;
    }
}
