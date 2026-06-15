package org.example.strategy;

import org.example.entities.Agent;
import org.example.entities.enums.IssueType;

public interface AssignmentStrategy {
    Agent assignAgent(String issueId, IssueType issueType);
}
