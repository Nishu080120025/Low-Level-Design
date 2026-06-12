package org.example.entities;

import org.example.entities.enums.VehicleType;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class ParkingSpot {
    private String spotId;
    private AtomicBoolean isOccupied;
    private VehicleType vehicleType;
    private String floorId;

    public ParkingSpot(String spotId, VehicleType vehicleType, String floorId) {

        this.spotId = spotId;
        this.isOccupied = new AtomicBoolean(false);
        this.vehicleType = vehicleType;
        this.floorId = floorId;
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

    public String getFloorId() {
        return this.floorId;
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
                ", floorId=" + floorId +
                '}';
    }
}
