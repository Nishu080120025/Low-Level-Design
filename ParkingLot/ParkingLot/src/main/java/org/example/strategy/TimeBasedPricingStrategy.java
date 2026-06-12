package org.example.strategy;

import org.example.entities.enums.VehicleType;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeBasedPricingStrategy implements PricingStrategy{

    @Override
    public double calculatePrice(VehicleType vehicleType, LocalDateTime entryTime,LocalDateTime exitTime){
        long minutes = Duration.between(entryTime,exitTime).toMinutes();
        long hoursParked = (long) Math.ceil(minutes / 60.0);
        double baseRate= getBaseRate(vehicleType);
        return baseRate * hoursParked;
    }

    private double getBaseRate(VehicleType vehicleType){
        switch(vehicleType){
            case CAR:
                return 20.00;
            case TRUCK:
                return 35.00;
            case MOTORCYCLE:
                return 10.00;
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + vehicleType);
        }
    }
}
