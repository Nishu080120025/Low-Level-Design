package org.example.state;

import org.example.model.Direction;
import org.example.model.entity.Elevator;

public class MoveUpState implements ElevatorState {

    @Override
    public void move(Elevator elevator) {
        if (elevator.getUpRequest().isEmpty()) {
            elevator.setIdle();
            System.out.println("No up requests. Elevator is now idle.");
            return;
        }
        int targetFloor = elevator.getUpRequest().first();
        elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
        if (targetFloor == elevator.getCurrentFloor()) {
            elevator.getUpRequest().pollFirst();
            System.out.println("Arrived at floor " + targetFloor + ". Doors opening...");
            elevator.openDoor();
        }
        if (elevator.getUpRequest().isEmpty()) {
            elevator.setIdle();
            elevator.closeDoor();
            System.out.println("No more up requests. Elevator is now idle.");
        }

    }

    @Override
    public Direction getDirection() {
        return Direction.UP;
    }
}
