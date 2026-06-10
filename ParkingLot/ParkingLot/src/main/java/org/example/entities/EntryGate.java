package org.example.entities;

import org.example.manager.ParkingSpotManager;
import org.example.service.TicketService;

import java.time.LocalDateTime;

public class EntryGate {
    private String gateId;
    private TicketService ticketService;

    public EntryGate(String gateId, TicketService ticketService) {
        this.gateId = gateId;
        this.ticketService = ticketService;
    }

    public String getGateId() {
        return gateId;
    }

    public void parkVehicle(String vehicleId, ParkingSpotManager parkingSpotManager, LocalDateTime startTime, VehicleType vehicleType) {
        parkingSpotManager.parkVehicle(vehicleId, vehicleType);
        String parkingSpotId = parkingSpotManager.getParkingSpotByVehicleId(vehicleId).getSpotId();
        ticketService.bookTicket(vehicleId, parkingSpotId, startTime);
        String ticketId = ticketService.getTicketByVehicleId(vehicleId).getTicketId();
        System.out.println("Vehicle " + vehicleId + " parked at gate " + gateId + " in spot " + parkingSpotId + " with ticket ID " + ticketId);
    }

    @Override
    public String toString() {
        return "EntryGate{" +
                "gateId='" + gateId + '\'' +
                '}';
    }


}
