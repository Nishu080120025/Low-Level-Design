package org.example.entities;

public class CarParkingSpot extends ParkingSpot{
    public CarParkingSpot(String id,Integer floorNumber) {
        super(id,VehicleType.CAR,floorNumber);
    }
}
