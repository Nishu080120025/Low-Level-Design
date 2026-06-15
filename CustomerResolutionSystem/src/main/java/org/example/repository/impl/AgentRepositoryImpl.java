package org.example.repository.impl;

import org.example.entities.Agent;
import org.example.entities.enums.IssueType;
import org.example.repository.AgentRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AgentRepositoryImpl implements AgentRepository {
    private HashMap<String, Agent> agentMap;

    @Override
    public void addAgent(String agentId, Agent agent) {
        agentMap.put(agentId, agent);
    }

    @Override
    public Agent removeAgent(String agentId) {
        return agentMap.remove(agentId);
    }

    @Override
    public Agent updateAgent(Agent agent) {
        return agentMap.put(agent.getUserId(), agent);
    }

    @Override
    public List<Agent> getAllAgents() {
        return new ArrayList<>(agentMap.values());
    }

    @Override
    public Agent getAgent(String agentId) {
        return agentMap.getOrDefault(agentId, null);
    }

    @Override
    public List<Agent> getAllAgentsByIssueType(IssueType issueType) {
        List<Agent> agentsWithIssueType = new ArrayList<>();
        for (Agent agent : agentMap.values()) {
            if (agent.getExpertise().contains(issueType)) {
                agentsWithIssueType.add(agent);
            }
        }
        return agentsWithIssueType;
    }
}
