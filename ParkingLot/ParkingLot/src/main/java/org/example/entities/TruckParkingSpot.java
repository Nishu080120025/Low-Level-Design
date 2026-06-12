package org.example.entities;

import org.example.entities.enums.VehicleType;

public class TruckParkingSpot extends ParkingSpot{
    public TruckParkingSpot(String id,String floorNumber){
        super(id, VehicleType.TRUCK,floorNumber);
    }
}
