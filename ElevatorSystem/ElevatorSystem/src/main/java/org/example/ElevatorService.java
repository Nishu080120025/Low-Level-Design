package org.example;

import org.example.model.requests.ExternalRequest;

import java.util.List;

public interface ElevatorService {
    void handleExternalRequests(List<ExternalRequest> externalRequestList);
    void handleInternalRequest(int id, int targetFloor);
    void startElevators();
    void shutDownElevators();
}
