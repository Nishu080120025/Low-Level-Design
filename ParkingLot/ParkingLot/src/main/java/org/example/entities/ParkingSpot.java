package org.example.entities;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public abstract class ParkingSpot {
    private String spotId;
    private AtomicBoolean isOccupied;
    private VehicleType vehicleType;
    private Integer floorNumber;

    public ParkingSpot(String spotId, VehicleType vehicleType, Integer floorNumber) {

        this.spotId = spotId;
        this.isOccupied = new AtomicBoolean(false);
        this.vehicleType = vehicleType;
        this.floorNumber = floorNumber;
    }

    public String getSpotId() {
        return spotId;
    }

    public boolean isOccupied() {
        return isOccupied.get();
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Integer getFloorNumber() {
        return this.floorNumber;
    }

    public boolean occupySpot() {
        return isOccupied.compareAndSet(false, true);
    }

    public boolean releaseSpot() {
        return isOccupied.compareAndSet(true, false);
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "spotId='" + spotId + '\'' +
                ", isOccupied=" + isOccupied +
                ", vehicleType=" + vehicleType +
                ", floorNumber=" + floorNumber +
                '}';
    }
}
