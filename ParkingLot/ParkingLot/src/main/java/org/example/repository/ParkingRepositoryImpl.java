package org.example.repository;

import org.example.entities.ParkingSpot;

import java.util.HashMap;

public class ParkingRepositoryImpl implements ParkingRepository {
    private HashMap<String , ParkingSpot>vehicleParkingSpotHashMap;
    private HashMap<String , ParkingSpot>parkingSpotHashMap;

    public ParkingRepositoryImpl() {
        this.parkingSpotHashMap = new HashMap<>();
        this.vehicleParkingSpotHashMap=new HashMap<>();
    }

    @Override
    public void addVehicle(String vehicleId,String parkingSpotId){
        ParkingSpot parkingSpot =parkingSpotHashMap.get(parkingSpotId);
       if(parkingSpot==null){
           throw new RuntimeException("Parking spot not found");
       }
       vehicleParkingSpotHashMap.put(vehicleId,parkingSpot);
       System.out.println("Vehicle "+vehicleId+" parked at spot "+parkingSpotId);

    }
    @Override
    public void removeVehicle(String vehicleId){
        ParkingSpot parkingSpot =vehicleParkingSpotHashMap.get(vehicleId);
        if(parkingSpot==null){
            throw new RuntimeException("Vehicle not found");
        }
        vehicleParkingSpotHashMap.remove(vehicleId);
        System.out.println("Vehicle "+vehicleId+" removed from spot "+parkingSpot.getSpotId());
    }

    @Override
    public ParkingSpot getParkingSpot(String parkingSpotId){
        return parkingSpotHashMap.getOrDefault(parkingSpotId,null);
    }

    @Override
    public ParkingSpot getParkingSpotByVehicleId(String vehicleId) {
        return vehicleParkingSpotHashMap.getOrDefault(vehicleId, null);
    }
}
