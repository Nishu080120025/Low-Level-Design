package org.example.strategy;

import org.example.entities.VehicleType;

import java.time.LocalDateTime;

public interface PricingStrategy {

    double calculatePrice(VehicleType vehicleType, LocalDateTime startTime, LocalDateTime endTime);

}
