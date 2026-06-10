package org.example;

import org.example.entities.EntryGate;
import org.example.entities.ExitGate;
import org.example.entities.ParkingSpot;
import org.example.entities.VehicleType;
import org.example.manager.ParkingSpotManager;
import org.example.repository.ParkingRepository;
import org.example.service.TicketService;
import org.example.strategy.PaymentProcessor;
import org.example.strategy.PricingStrategy;

import java.time.LocalDateTime;

public class ParkingLot {
    private ParkingSpotManager parkingSpotManager;
    private ExitGate exitGate;
    private EntryGate entryGate;
    private TicketService ticketService;
    private PaymentProcessor paymentProcessor;
    private PricingStrategy pricingStrategy;
    private ParkingRepository parkingRepository;
    public static ParkingLot Instance = new ParkingLot();


    public static ParkingLot getInstance() {
        return Instance;
    }

    private ParkingLot() {

    }

    public void initialize(ExitGate exitGate, EntryGate entryGate, TicketService ticketService, PaymentProcessor paymentProcessor, PricingStrategy pricingStrategy, ParkingSpotManager parkingSpotManager, ParkingRepository parkingRepository) {
        this.exitGate = exitGate;
        this.entryGate = entryGate;
        this.ticketService = ticketService;
        this.paymentProcessor = paymentProcessor;
        this.pricingStrategy = pricingStrategy;
        this.parkingSpotManager = parkingSpotManager;
        this.parkingRepository=parkingRepository;
    }

    public void parkVehicle(String vehicleId, VehicleType vehicleType, LocalDateTime entryTime) {
        entryGate.parkVehicle(vehicleId,parkingSpotManager,entryTime,vehicleType);
        System.out.println("Vehicle " + vehicleId + " parked at " + entryTime);
    }

    public void unparkVehicle(String vehicleId,LocalDateTime endTime){
        ParkingSpot parkingSpot=parkingRepository.getParkingSpotByVehicleId(vehicleId);
        String parkingSpotId = parkingSpot.getSpotId();
        VehicleType vehicleType=parkingSpot.getVehicleType();
        String ticketId =ticketService.getTicketByVehicleId(vehicleId).getTicketId();
        exitGate.unparkVehicle(vehicleId,parkingSpotId,parkingSpotManager,paymentProcessor,ticketId,pricingStrategy,vehicleType,endTime);
        System.out.println("Vehicle " + vehicleId + " unparked at " + endTime);
    }

}
