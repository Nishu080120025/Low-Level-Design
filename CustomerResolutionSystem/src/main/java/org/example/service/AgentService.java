package org.example.service;

import org.example.entities.Issues;
import org.example.entities.enums.ResolutionStatus;

import java.util.List;

public interface AgentService {

    void resolveIssue(String issueId,String resolutionDetails);
    List<String> viewAssignedIssues(String agentId);

}
