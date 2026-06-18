package org.example.entities;

import org.example.entities.enums.IssueType;
import org.example.entities.enums.ResolutionStatus;
import org.example.entities.enums.UserRole;

import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Agent extends Users {
    private List<String> assignedTickets;
    private Queue<String> waitingTickets;
    private Set<IssueType> expertise;
    private String currentIssue;

    public Agent(String agentId, String name, List<String> assignedTickets, String email, String phoneNumber, Queue<String> waitingTickets, String currentIssue, Set<IssueType> expertise) {
        super(agentId, name, UserRole.AGENT, email, phoneNumber);
        this.assignedTickets = assignedTickets;
        this.waitingTickets = waitingTickets;
        this.currentIssue = currentIssue;
        this.expertise = expertise;

    }

    public List<String> getAssignedTickets() {
        return assignedTickets;
    }

    public void setAssignedTickets(List<String> assignedTickets) {
        this.assignedTickets = assignedTickets;
    }

    public Queue<String> getWaitingTickets() {
        return waitingTickets;
    }

    public String getCurrentIssue() {
        return currentIssue;
    }

    public boolean isAvailable() {
        if (waitingTickets.isEmpty() && currentIssue == null) {
            return true;
        }
        return false;
    }

    public Set<IssueType> getExpertise() {
        return expertise;
    }

    public IssueType addExpertise(IssueType issueType) {
        expertise.add(issueType);
        return issueType;
    }

    public String addIssue(String issueId) {
        if (currentIssue == null) {
            currentIssue = issueId;
        } else {
            waitingTickets.add(issueId);
        }
        assignedTickets.add(issueId);
        return issueId;
    }

    public String promoteWaitingIssueToCurrent(){
        if(currentIssue==null && !waitingTickets.isEmpty()){
            currentIssue=waitingTickets.poll();
            return currentIssue;
        }
        return null;
    }

    public String removeWaitingIssue(String issueId){
        waitingTickets.remove(issueId);
        return "Issue with ID "+issueId+" removed from waiting tickets.";
    }


}
