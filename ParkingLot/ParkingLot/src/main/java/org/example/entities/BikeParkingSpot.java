package org.example.entities;

public class BikeParkingSpot extends ParkingSpot{
    public BikeParkingSpot(String id,String floorNumber){
        super(id,VehicleType.MOTORCYCLE,floorNumber);
    }

}
