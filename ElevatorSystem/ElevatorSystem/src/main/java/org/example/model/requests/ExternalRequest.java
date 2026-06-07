package org.example.model.requests;

import org.example.model.Direction;

public class ExternalRequest extends ElevatorRequest {
    private Direction direction;

    public ExternalRequest(int floor, Direction direction) {
        super(floor);
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "ExternalRequest{" +
                "floor=" + this.getFloor() +
                ", direction=" + direction +
                '}';
    }
}
