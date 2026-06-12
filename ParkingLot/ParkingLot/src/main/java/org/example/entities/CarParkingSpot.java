package org.example.entities;

import org.example.entities.enums.VehicleType;

public class CarParkingSpot extends ParkingSpot{
    public CarParkingSpot(String id,String floorNumber) {
        super(id, VehicleType.CAR,floorNumber);
    }
}
