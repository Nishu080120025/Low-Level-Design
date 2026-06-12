package org.example.factory;

import org.example.entities.CarParkingSpot;
import org.example.entities.ParkingSpot;
import org.example.entities.TruckParkingSpot;
import org.example.entities.enums.VehicleType;

public class ParkingSpotFactory {
    public static ParkingSpot createParkingSpot(VehicleType vehicleType, String spotId, String floorId) {
        switch (vehicleType) {
            case CAR:
                return new CarParkingSpot(spotId, floorId);

            case TRUCK:
                return new TruckParkingSpot(spotId, floorId);

            case MOTORCYCLE:
                return new CarParkingSpot(spotId, floorId);

            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + vehicleType);
        }
    }
}
