package org.example.state;

import org.example.model.Direction;
import org.example.model.entity.Elevator;
import org.example.model.requests.ElevatorRequest;

public interface ElevatorState {

    void move(Elevator elevator);

    Direction getDirection();
}
