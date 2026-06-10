package org.example.manager;

import org.example.entities.ParkingSpot;
import org.example.entities.VehicleType;
import org.example.repository.ParkingRepository;

import java.util.Map;
import java.util.Queue;

public class ParkingSpotManager {
    private Map<VehicleType, Queue<ParkingSpot>> parkingSpots;
    private final ParkingRepository parkingRepository;
    public ParkingSpotManager(Map<VehicleType, Queue<ParkingSpot>> parkingSpots,ParkingRepository parkingRepository) {
        this.parkingSpots = parkingSpots;
        this.parkingRepository=parkingRepository;
    }

    public void parkVehicle(String vehicleId,VehicleType vehicleType){
        Queue<ParkingSpot>availableParkingSpot=parkingSpots.get(vehicleType);
        if(availableParkingSpot.isEmpty()){
            System.out.println("No parking spot available for vehicle type: "+vehicleType);
            return;
        }
        ParkingSpot parkingSpot=availableParkingSpot.poll();
        boolean res=parkingSpot.occupySpot();
        if(!res){
            System.out.println("Failed to occupy parking spot "+parkingSpot.getSpotId());
            return;
        }
        parkingRepository.addVehicle(vehicleId,parkingSpot.getSpotId());
        System.out.println("Vehicle "+vehicleId+" parked at spot "+parkingSpot.getSpotId());
    }

    public void unparkVehicle(String vehicleId,VehicleType vehicleType){
        ParkingSpot parkingSpot=parkingRepository.getParkingSpotByVehicleId(vehicleId);
        if(parkingSpot==null){
            System.out.println("Vehicle "+vehicleId+" not found in parking lot");
            return;
        }
        parkingSpot.releaseSpot();
        Queue<ParkingSpot>availableParkingSpot=parkingSpots.get(parkingSpot.getVehicleType());
        availableParkingSpot.offer(parkingSpot);
        parkingRepository.removeVehicle(vehicleId);
        System.out.println("Vehicle "+vehicleId+" unparked from spot "+parkingSpot.getSpotId());
    }

    public ParkingSpot getParkingSpotByVehicleId(String vehicleId){
        return parkingRepository.getParkingSpotByVehicleId(vehicleId);
    }

}
