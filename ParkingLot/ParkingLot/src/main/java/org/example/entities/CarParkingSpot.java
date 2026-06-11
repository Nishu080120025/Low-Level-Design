package org.example.entities;

public class CarParkingSpot extends ParkingSpot{
    public CarParkingSpot(String id,String floorNumber) {
        super(id,VehicleType.CAR,floorNumber);
    }
}
