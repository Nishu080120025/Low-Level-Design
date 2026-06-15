package org.example.service;

import org.example.entities.Agent;
import org.example.entities.Issues;
import org.example.entities.enums.IssueType;

import java.util.List;
import java.util.Map;

public interface AdminService {
    Agent addAgent(String agentEmail, String agentName, List<IssueType> expertiseList);
    void removeAgent(String agentId);
    Agent updateAgent(String agentId,String email,String name,List<IssueType>expertiseList,String phoneNumber);
    List<Agent>viewAllAgent();
    Map<String,List<Issues>> viewAgentsWorkHistory();
}
