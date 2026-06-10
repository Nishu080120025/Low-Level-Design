package org.example.entities;

import java.util.List;
import java.util.Map;

public class Floor {
    private String floorId;
    private Map<VehicleType, List<ParkingSpot>> parkingSpotList;

    public Floor(String floorId, Map<VehicleType, List<ParkingSpot>> parkingSpotList) {
        this.floorId = floorId;
        this.parkingSpotList = parkingSpotList;
    }

}
