package org.example.repository;

import org.example.entities.Agent;
import org.example.entities.Issues;
import org.example.entities.enums.IssueType;

import java.util.List;
import java.util.Queue;

public interface AgentRepository {

    void addAgent(String agentId, Agent agent);

    Agent removeAgent(String agentId);

    Agent updateAgent(Agent agent);

    Agent getAgent(String agentId);

    List<Agent> getAllAgents();

    List<Agent> getAllAgentsByIssueType(IssueType issueType);


}
