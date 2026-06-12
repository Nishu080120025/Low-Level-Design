package org.example.entities;

import org.example.entities.enums.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Floor {
    private String floorId;
    private Map<VehicleType, List<ParkingSpot>> parkingSpotList;

    public Floor(String floorId, Map<VehicleType, List<ParkingSpot>> parkingSpotList) {
        this.floorId = floorId;
        this.parkingSpotList = parkingSpotList;
    }

    public String getFloorId() {
        return floorId;
    }

    public Map<VehicleType, List<ParkingSpot>> getParkingSpotList() {
        return parkingSpotList;
    }

    public ParkingSpot addParkingSpot(VehicleType vehicleType,ParkingSpot parkingSpot){
        parkingSpotList.putIfAbsent(vehicleType,new ArrayList<>());
        List<ParkingSpot>parkingSpotsList=parkingSpotList.get(vehicleType);
        parkingSpotsList.add(parkingSpot);
        return parkingSpot;
    }

}
