package org.example.entities;

public class TruckParkingSpot extends ParkingSpot{
    public TruckParkingSpot(String id,String floorNumber){
        super(id,VehicleType.TRUCK,floorNumber);
    }
}
