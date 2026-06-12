package org.example.entities;

import org.example.entities.enums.VehicleType;

public class BikeParkingSpot extends ParkingSpot{
    public BikeParkingSpot(String id,String floorNumber){
        super(id, VehicleType.MOTORCYCLE,floorNumber);
    }

}
