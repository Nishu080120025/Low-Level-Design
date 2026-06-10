package org.example.strategy;

import org.example.entities.VehicleType;

import java.time.LocalDateTime;

public class EventBasedPricingStrategy implements PricingStrategy{

    @Override
    public double calculatePrice(VehicleType vehicleType, LocalDateTime entryTime,LocalDateTime exitTime){
        double basePrice=getBasePrice(vehicleType);
        int peakHours=calculatePeakHours(entryTime,exitTime);
        int totalHours=calculateTotalHours(entryTime,exitTime);
        long offPeakHours=totalHours-peakHours;
        double peakHoursPrice=basePrice*1.5;
        double price=peakHoursPrice*peakHours+basePrice*offPeakHours;
        return price;

    }
    private boolean isPeakHours(LocalDateTime time){
        int hour =time.getHour();
        return hour>=10 && hour<=22;
    }
    private int calculatePeakHours(LocalDateTime entryTime,LocalDateTime exitTime){
        int peakHours=0;
        LocalDateTime currentTime=entryTime;
        while(currentTime.isBefore(exitTime)){
            if(isPeakHours((currentTime))){
                peakHours++;
            }
            currentTime=currentTime.plusHours(1);
        }
        return peakHours;
    }
    private int calculateTotalHours(LocalDateTime entryTime,LocalDateTime exitTime){
        return (int) java.time.Duration.between(entryTime,exitTime).toHours();
    }
    private double getBasePrice(VehicleType vehicleType) {
        switch (vehicleType) {
            case CAR:
                return 50.0;
            case TRUCK:
                return 100.0;
            case MOTORCYCLE:
                return 30.0;
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + vehicleType);

        }
    }
}
