package org.example.repository;

import org.example.entities.Ticket;

import java.time.LocalDateTime;

public interface TicketRepository {

    void bookTicket(String vehicleId, String parkingLotId, LocalDateTime startTime);
    void cancelTicket(String ticketId);
    Ticket getTicketById(String ticketId);
    String generateTicketId(String vehicleId, String parkingLotId, LocalDateTime startTime);
    void updateTicket(Ticket ticket);
    Ticket getTicketByVehicleId(String vehicleId);

}
