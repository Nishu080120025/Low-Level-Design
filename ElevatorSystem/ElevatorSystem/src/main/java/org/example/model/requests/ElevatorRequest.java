package org.example.model.requests;

public abstract class ElevatorRequest {
    private int floor;

    public ElevatorRequest(int floor) {
        this.floor = floor;
    }

    public int getFloor() {
        return floor;
    }

}

