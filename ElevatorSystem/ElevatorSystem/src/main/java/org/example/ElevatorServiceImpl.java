package org.example;

import org.example.model.Direction;
import org.example.model.entity.Elevator;
import org.example.model.requests.ExternalRequest;
import org.example.model.requests.InternalRequest;
import org.example.strategy.ElevatorAssigningStrategy;
import org.example.strategy.NearestElevatorAssigningStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ElevatorServiceImpl implements ElevatorService {

    private HashMap<Integer, Elevator> elevatorMap;
    private ExecutorService executorService;
    private ElevatorAssigningStrategy elevatorAssigningStrategy;

    public ElevatorServiceImpl(HashMap<Integer, Elevator> elevatorMap, ExecutorService executorService, ElevatorAssigningStrategy elevatorAssigningStrategy, int numOfElevators) {
        this.executorService = Executors.newFixedThreadPool(numOfElevators);
        this.elevatorMap = elevatorMap;
        this.elevatorAssigningStrategy = new NearestElevatorAssigningStrategy();
    }

    @Override
    public void startElevators() {
        for (int key : elevatorMap.keySet()) {
            Elevator elevator = elevatorMap.get(key);
            executorService.submit(elevator);
        }
    }

    @Override
    public void handleExternalRequests(List<ExternalRequest> externalRequestList) {
        for (ExternalRequest request : externalRequestList) {
            Elevator assignedElevator = elevatorAssigningStrategy.findBestElevator(elevatorMap, request);
            if (assignedElevator != null) {
                assignedElevator.addRequest(request);
                System.out.println("Assigned Elevator " + assignedElevator.getId() + " to External Request at floor " + request.getFloor() + " going " + assignedElevator.getDirection());
            } else {
                System.out.println("No available elevator for External Request at floor " + request.getFloor() + " going " + request.getDirection());
            }
        }
    }

    @Override
    public void handleInternalRequest(int id, int targetFloor) {
        Elevator elevator = elevatorMap.get(id);
        InternalRequest request = new InternalRequest(targetFloor);
        if (elevator != null) {
            elevator.addRequest(request);
            System.out.println("Added Internal Request to Elevator " + id + " for floor " + request.getFloor());
        } else {
            System.out.println("No elevator found with id " + id + " for Internal Request to floor " + request.getFloor());
        }
    }

    @Override
    public void shutDownElevators() {
        executorService.shutdownNow();
    }
}
