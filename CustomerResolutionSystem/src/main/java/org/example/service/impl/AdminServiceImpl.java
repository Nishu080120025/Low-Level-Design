package org.example.service.impl;

import org.example.entities.Agent;
import org.example.entities.Issues;
import org.example.entities.enums.IssueType;
import org.example.repository.AgentRepository;
import org.example.repository.IssueRepository;
import org.example.service.AdminService;
import org.example.util.IdUtil;

import java.util.*;

public class AdminServiceImpl implements AdminService {
    private AgentRepository agentRepository;
    private IdUtil idUtil;
    private IssueRepository issueRepository;

    public AdminServiceImpl(AgentRepository agentRepository, IssueRepository issueRepository, IdUtil idUtil) {
        this.agentRepository = agentRepository;
        this.issueRepository = issueRepository;
        this.idUtil = idUtil;
    }

    @Override
    public Agent addAgent(String agentEmail, String agentName, List<IssueType> expertiseList){
        String agentId=idUtil.generateUniqueId();
        HashSet<IssueType>issueTypeSet=new HashSet<>(expertiseList);
        Agent agent=new Agent(agentId,agentName,new ArrayList<Issues>(),agentEmail,null,new LinkedList<>(),null,issueTypeSet);
        agentRepository.addAgent(agentId,agent);
        return agent;
    }

    @Override
    public void removeAgent(String agentId){
        agentRepository.removeAgent(agentId);
        System.out.println("Agent with ID "+agentId+" removed successfully.");
    }

    @Override
    public Agent updateAgent(String agentId,String email,String name,List<IssueType>expertiseList,String phoneNumber){
        Agent agent=agentRepository.getAgent(agentId);
        if(agent==null){
            System.out.println("Agent with ID "+agentId+" not found.");
            return null;
        }
        agent.setEmail(email);
        agent.setName(name);
        agent.setPhoneNumber(phoneNumber);
        HashSet<IssueType>issueTypeSet=new HashSet<>(expertiseList);
        agent.getExpertise().clear();
        agent.getExpertise().addAll(issueTypeSet);
        agentRepository.updateAgent(agent);
        return agent;
    }

    @Override
    public List<Agent>viewAllAgent(){
        return agentRepository.getAllAgents();
    }

    @Override
    public HashMap<String ,List<Issues>> viewAgentsWorkHistory(){
        HashMap<String,List<Issues>>agentsHistoryMap=new HashMap<>();
        List<Agent>agentList=agentRepository.getAllAgents();
        for(Agent agent:agentList){
            agentsHistoryMap.putIfAbsent(agent.getUserId(),new ArrayList<>());
            List<Issues>issueList=agent.getAssignedTickets();
            agentsHistoryMap.get(agent.getUserId()).addAll(issueList);
        }
        return agentsHistoryMap;
    }
}
