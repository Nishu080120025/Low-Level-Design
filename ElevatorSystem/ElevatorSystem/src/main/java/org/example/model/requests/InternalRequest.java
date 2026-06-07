package org.example.model.requests;

public class InternalRequest extends ElevatorRequest {
    public InternalRequest(int floor) {
        super(floor);
    }


    @Override
    public String toString() {
        return "InternalRequest{floor=" + this.getFloor() + "}";
    }
}
