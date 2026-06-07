package org.example.state;

import org.example.model.Direction;
import org.example.model.entity.Elevator;

public class IdleState implements ElevatorState {

    @Override
    public void move(Elevator elevator) {
        if (!elevator.getUpRequest().isEmpty()) {
            elevator.setState(new MoveUpState());
            elevator.setDirection(Direction.UP);
        } else if (!elevator.getDownRequest().isEmpty()) {
            elevator.setState(new MoveDownState());
            elevator.setDirection(Direction.DOWN);
        } else {
            System.out.println("Elevator is idle at floor " + elevator.getCurrentFloor());
        }
    }

    @Override
    public Direction getDirection() {
        return Direction.IDLE;
    }

}
