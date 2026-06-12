package org.example;

import org.example.entities.EntryGate;
import org.example.entities.ExitGate;
import org.example.entities.ParkingSpot;
import org.example.entities.enums.VehicleType;
import org.example.manager.ParkingSpotManager;
import org.example.repository.ParkingRepository;
import org.example.service.TicketService;
import org.example.strategy.PaymentProcessor;
import org.example.strategy.PricingStrategy;

import java.time.LocalDateTime;
import java.util.List;

public class ParkingLot {
    private ParkingSpotManager parkingSpotManager;
    private List<ExitGate> exitGates;
    private List<EntryGate> entryGates;
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

    public void initialize(List<ExitGate> exitGates, List<EntryGate> entryGates, TicketService ticketService, PaymentProcessor paymentProcessor, PricingStrategy pricingStrategy, ParkingSpotManager parkingSpotManager, ParkingRepository parkingRepository) {
        this.exitGates = exitGates;
        this.entryGates = entryGates;
        this.ticketService = ticketService;
        this.paymentProcessor = paymentProcessor;
        this.pricingStrategy = pricingStrategy;
        this.parkingSpotManager = parkingSpotManager;
        this.parkingRepository = parkingRepository;
    }

    public void parkVehicle(String gateId, String vehicleId, VehicleType vehicleType, LocalDateTime entryTime) {
        EntryGate entryGate = entryGates.stream().
                filter(gate -> gate.getGateId().equals(gateId))
                .findFirst()
                .orElse(null);
        if (entryGate == null) {
            System.out.println("Invalid entry gate ID: " + gateId);
            return;
        }
        entryGate.parkVehicle(vehicleId, parkingSpotManager, entryTime, vehicleType);
        System.out.println("Vehicle " + vehicleId + " parked at " + entryTime);
    }

    public void unparkVehicle(String exitGateId, String vehicleId, LocalDateTime endTime) {
        ParkingSpot parkingSpot = parkingRepository.getParkingSpotByVehicleId(vehicleId);
        String parkingSpotId = parkingSpot.getSpotId();
        VehicleType vehicleType = parkingSpot.getVehicleType();
        String ticketId = ticketService.getTicketByVehicleId(vehicleId).getTicketId();
        ExitGate exitGate = exitGates.stream().
                filter(gate -> gate.getExitGateId().equals(exitGateId))
                .findFirst()
                .orElse(null);
        exitGate.unparkVehicle(vehicleId, parkingSpotId, parkingSpotManager, paymentProcessor, ticketId, pricingStrategy, vehicleType, endTime);
        System.out.println("Vehicle " + vehicleId + " unparked at " + endTime);
    }

}
