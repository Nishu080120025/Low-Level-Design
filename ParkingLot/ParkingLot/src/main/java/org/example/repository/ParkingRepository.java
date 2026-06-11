package org.example.repository;

import org.example.entities.ParkingSpot;

public interface ParkingRepository {
    void addVehicle(String vehicleId,String parkingSpotId);
    void removeVehicle(String vehicleId);
    ParkingSpot getParkingSpot(String parkingSpotId);
    ParkingSpot getParkingSpotByVehicleId(String vehicleId);
    void initializeParkingSpot(java.util.HashMap<String, ParkingSpot> spotMap, java.util.HashMap<String, ParkingSpot> vehicleSpotMap);

    void addParkingSpot(String parkingSpotId, ParkingSpot parkingSpot);
}
