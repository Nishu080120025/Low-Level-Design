package org.example.service;

import org.example.entities.PaymentStatus;
import org.example.entities.Ticket;

import java.time.LocalDateTime;

public interface TicketService {
    void bookTicket(String vehicleId, String parkingSpotId,LocalDateTime entryTime);
    void cancelTicket(String ticketId);
    void updateTicket(String ticketId, LocalDateTime endTime, PaymentStatus paymentStatus);
    Ticket getTicketById(String ticketId);

    Ticket getTicketByVehicleId(String vehicleId);
}
