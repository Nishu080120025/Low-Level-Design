package org.example.state;

import org.example.model.Direction;
import org.example.model.entity.Elevator;

public class MoveDownState implements ElevatorState {

    @Override
    public void move(Elevator elevator) {
        if (elevator.getDownRequest().isEmpty()) {
            elevator.setIdle();
            System.out.println("No down requests. Elevator is now idle.");
            return;
        }

        int targetFloor = elevator.getDownRequest().last();
        elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
        if (targetFloor == elevator.getCurrentFloor()) {
            elevator.getDownRequest().pollFirst();
            elevator.openDoor();
            System.out.println("Arrived at floor " + targetFloor + ". Serving down request.");
        }

        if (elevator.getDownRequest().isEmpty()) {
            elevator.setIdle();
            elevator.closeDoor();
            System.out.println("No more down requests. Elevator is now idle.");
        }
    }

    @Override
    public Direction getDirection() {
        return Direction.DOWN;
    }
}
