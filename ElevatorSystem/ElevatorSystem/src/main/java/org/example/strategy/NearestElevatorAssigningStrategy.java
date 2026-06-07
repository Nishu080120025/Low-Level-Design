package org.example.strategy;

import org.example.model.Direction;
import org.example.model.entity.Elevator;
import org.example.model.requests.ExternalRequest;

import java.util.HashMap;

public class NearestElevatorAssigningStrategy implements ElevatorAssigningStrategy {

    @Override
    public Elevator findBestElevator(HashMap<Integer, Elevator> elevatorHashMap, ExternalRequest request) {
        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;
        for (Elevator elevator : elevatorHashMap.values()) {
            if (elevator.getDirection() == Direction.IDLE || elevator.getDirection() == request.getDirection()) {
                int distance = Math.abs(elevator.getCurrentFloor() - request.getFloor());
                if (distance < minDistance) {
                    minDistance = distance;
                    bestElevator = elevator;
                }
            }
        }
        return bestElevator;
    }
}
