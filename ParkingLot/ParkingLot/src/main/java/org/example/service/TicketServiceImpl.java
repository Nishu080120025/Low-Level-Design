package org.example.service;

import org.example.entities.PaymentStatus;
import org.example.entities.Ticket;
import org.example.repository.TicketRepository;

import java.time.LocalDateTime;

public class TicketServiceImpl implements TicketService{
    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    @Override
    public void bookTicket(String vehicleId, String parkingSpotId, LocalDateTime entryTime){
        ticketRepository.bookTicket(vehicleId,parkingSpotId,entryTime);
        System.out.println("Ticket booked successfully for vehicle: " + vehicleId + " at parking spot: " + parkingSpotId);
    }
    @Override
    public void cancelTicket(String ticketId){
        ticketRepository.cancelTicket(ticketId);
        System.out.println("Ticket with ID: " + ticketId + " has been cancelled.");
    }

    @Override
    public Ticket getTicketById(String ticketId){
        return ticketRepository.getTicketById(ticketId);
    }

    @Override
    public void updateTicket(String ticketId, LocalDateTime exitTime, PaymentStatus paymentStatus){
        Ticket ticket=ticketRepository.getTicketById(ticketId);
        if(ticket!=null){
            ticket.setExitTime(exitTime);
            ticket.setPaymentStatus(paymentStatus);
        }
        ticketRepository.updateTicket(ticket);
    }

    @Override
    public Ticket getTicketByVehicleId(String vehicleId){
        return ticketRepository.getTicketByVehicleId(vehicleId);
    }
}
