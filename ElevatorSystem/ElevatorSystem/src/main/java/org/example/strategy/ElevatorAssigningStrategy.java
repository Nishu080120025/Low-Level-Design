package org.example.strategy;

import org.example.model.Direction;
import org.example.model.entity.Elevator;
import org.example.model.requests.ExternalRequest;

import java.util.HashMap;

public interface ElevatorAssigningStrategy {

    Elevator findBestElevator(HashMap<Integer,Elevator> elevatorHashMap, ExternalRequest request);
}
