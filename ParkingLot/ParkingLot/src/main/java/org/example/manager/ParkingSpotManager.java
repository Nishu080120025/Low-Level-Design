package org.example.manager;

import org.example.entities.Floor;
import org.example.entities.ParkingSpot;
import org.example.entities.enums.VehicleType;
import org.example.repository.ParkingRepository;

import java.util.*;

public class ParkingSpotManager {
    private Map<VehicleType, Queue<ParkingSpot>> parkingSpots;
    private final ParkingRepository parkingRepository;
    private List<Floor> floorList;

    public ParkingSpotManager(Map<VehicleType, Queue<ParkingSpot>> parkingSpots, ParkingRepository parkingRepository, List<Floor> floorList) {
        this.parkingSpots = new HashMap<>();
        this.parkingRepository = parkingRepository;
        this.floorList = floorList;
        initializeParkingSpots();
    }

    public void initializeParkingSpots() {
        for (Floor floor : floorList) {
            Map<VehicleType, List<ParkingSpot>> parkingSpotList = floor.getParkingSpotList();
            for (Map.Entry<VehicleType, List<ParkingSpot>> entry : parkingSpotList.entrySet()) {
                VehicleType vehicleType = entry.getKey();
                List<ParkingSpot> parkingSpots = entry.getValue();
                for (ParkingSpot parkingSpot : parkingSpots) {
                    if (!parkingSpot.isOccupied()) {
                        Queue<ParkingSpot> availableParkingSpots = this.parkingSpots.getOrDefault(vehicleType, null);
                        if (availableParkingSpots == null) {
                            availableParkingSpots = new LinkedList<>();
                            this.parkingSpots.put(vehicleType, availableParkingSpots);
                        }
                        availableParkingSpots.offer(parkingSpot);
                    }
                }
            }
        }
    }

    public int getAvailableSpots(VehicleType vehicleType) {
        Queue<ParkingSpot> availableParkingSpots = this.parkingSpots.getOrDefault(vehicleType, null);
        if (availableParkingSpots == null) {
            return 0;
        }
        return availableParkingSpots.size();
    }

    public void parkVehicle(String vehicleId, VehicleType vehicleType) {
        Queue<ParkingSpot> availableParkingSpot = parkingSpots.get(vehicleType);
        if (availableParkingSpot == null || availableParkingSpot.isEmpty()) {
            System.out.println("No parking spot available for vehicle type: " + vehicleType);
            return;
        }
        ParkingSpot parkingSpot = availableParkingSpot.poll();
        boolean res = parkingSpot.occupySpot();
        if (!res) {
            System.out.println("Failed to occupy parking spot " + parkingSpot.getSpotId());
            return;
        }
        parkingRepository.addVehicle(vehicleId, parkingSpot.getSpotId());
        System.out.println("Vehicle " + vehicleId + " parked at spot " + parkingSpot.getSpotId());
    }

    public void unparkVehicle(String vehicleId, VehicleType vehicleType) {
        ParkingSpot parkingSpot = parkingRepository.getParkingSpotByVehicleId(vehicleId);
        if (parkingSpot == null) {
            System.out.println("Vehicle " + vehicleId + " not found in parking lot");
            return;
        }
        parkingSpot.releaseSpot();
        Queue<ParkingSpot> availableParkingSpot = parkingSpots.get(parkingSpot.getVehicleType());
        availableParkingSpot.offer(parkingSpot);
        parkingRepository.removeVehicle(vehicleId);
        System.out.println("Vehicle " + vehicleId + " unparked from spot " + parkingSpot.getSpotId());
    }

    public ParkingSpot getParkingSpotByVehicleId(String vehicleId) {
        return parkingRepository.getParkingSpotByVehicleId(vehicleId);
    }

}
