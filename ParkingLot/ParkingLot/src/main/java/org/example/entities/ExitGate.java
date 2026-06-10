package org.example.entities;

import org.example.manager.ParkingSpotManager;
import org.example.service.TicketService;
import org.example.strategy.PaymentProcessor;
import org.example.strategy.PricingStrategy;

import java.time.LocalDateTime;

public class ExitGate {
    private int exitGateId;
    private final TicketService ticketService;
    public ExitGate(int exitGateId, TicketService ticketService) {
        this.exitGateId = exitGateId;
        this.ticketService = ticketService;
    }

    public int getExitGateId() {
        return exitGateId;
    }
    public void unparkVehicle(String vehicleId, String parkingSpotId, ParkingSpotManager parkingSpotManager, PaymentProcessor paymentProcessor, String ticketId, PricingStrategy pricingStrategy, VehicleType vehicleType,LocalDateTime exitTime) {
        Ticket ticket = ticketService.getTicketById(ticketId);
        double amount = pricingStrategy.calculatePrice(vehicleType,ticket.getEntryTime(), exitTime);
        paymentProcessor.processPayment(amount);
        ticketService.updateTicket(ticketId, exitTime,PaymentStatus.COMPLETED);
        parkingSpotManager.unparkVehicle(vehicleId,vehicleType);
        System.out.println("Vehicle with ID " + vehicleId + " has exited through gate " + exitGateId);
    }

    @Override
    public String toString() {
        return "ExitGate{" +
                "exitGateId=" + exitGateId +
                '}';
    }
}
