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
        boolean isMovingTowards = false;
        boolean isIdle = false;
        for (Elevator elevator : elevatorHashMap.values()) {
            isIdle = elevator.getDirection() == Direction.IDLE;
            if (elevator.getDirection() == Direction.UP && request.getFloor() >= elevator.getCurrentFloor()) {
                isMovingTowards = true;
            } else if (elevator.getDirection() == Direction.DOWN && request.getFloor() <= elevator.getCurrentFloor()) {
                isMovingTowards = true;
            }
            if (isIdle || isMovingTowards) {
                int distance = Math.abs(elevator.getCurrentFloor() - request.getFloor());
                if (distance < minDistance) {
                    minDistance = distance;
                    bestElevator = elevator;
                }
            }
        }

        if (bestElevator == null) {
            for (Elevator elevator : elevatorHashMap.values()) {
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
