package org.example.entities;

import org.example.entities.enums.IssueType;
import org.example.entities.enums.ResolutionStatus;
import org.example.entities.enums.UserRole;

import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Agent extends Users {
    private List<Issues> assignedTickets;
    private Queue<Issues> waitingTickets;
    private Set<IssueType> expertise;
    private Issues currentIssue;

    public Agent(String agentId, String name, List<Issues> assignedTickets, String email, String phoneNumber, Queue<Issues> waitingTickets, Issues currentIssue, Set<IssueType> expertise) {
        super(agentId, name, UserRole.AGENT, email, phoneNumber);
        this.assignedTickets = assignedTickets;
        this.waitingTickets = waitingTickets;
        this.currentIssue = currentIssue;
        this.expertise = expertise;

    }

    public List<Issues> getAssignedTickets() {
        return assignedTickets;
    }

    public void setAssignedTickets(List<Issues> assignedTickets) {
        this.assignedTickets = assignedTickets;
    }

    public Queue<Issues> getWaitingTickets() {
        return waitingTickets;
    }

    public Issues getCurrentIssue() {
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

    public Issues addIssue(Issues issue) {
        if (currentIssue == null) {
            currentIssue = issue;
        } else {
            waitingTickets.add(issue);
        }
        assignedTickets.add(issue);
        return issue;
    }

    public Issues resolveCurrentIssue() {
        Issues presentIssue = currentIssue;
        if (presentIssue != null) {
            presentIssue.setResolutionStatus(ResolutionStatus.RESOLVED);
            assignedTickets.stream().filter(issue -> issue.getIssueId().equals(presentIssue.getIssueId()))
                    .findFirst()
                    .ifPresent(issue -> issue.setResolutionStatus(ResolutionStatus.RESOLVED));
            if (!waitingTickets.isEmpty()) {
                currentIssue = waitingTickets.poll();
            }
        } else {
            System.out.println("No current issue to resolve.");
            return null;
        }
        return presentIssue;
    }

    public Issues resolveWaitingIssue(String issueId) {
        Iterator<Issues> issuesIterator = waitingTickets.iterator();
        while (issuesIterator.hasNext()) {
            Issues issue = issuesIterator.next();
            if (issue.getIssueId().equals(issueId)) {
                issue.setResolutionStatus(ResolutionStatus.RESOLVED);
                assignedTickets.stream().filter(i -> i.getIssueId().equals(issueId)).findFirst().ifPresent(i -> i.setResolutionStatus(ResolutionStatus.RESOLVED));
                issuesIterator.remove();
                return issue;
            }
        }
        return null;
    }
}
