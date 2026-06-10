package org.example.repository;

import org.example.entities.Ticket;

import java.time.LocalDateTime;
import java.util.HashMap;

public class TicketRepositoryImpl implements TicketRepository {
    private HashMap<String, Ticket>ticketHashMap=new HashMap<>();

    @Override
    public void bookTicket(String vehicleId, String parkingSpotId, LocalDateTime startTime){
        String ticketId=generateTicketId(vehicleId,parkingSpotId,startTime);
        Ticket ticket=new Ticket(ticketId,vehicleId,parkingSpotId,startTime);
        ticketHashMap.put(ticketId,ticket);
        System.out.println("Ticket booked successfully: "+ticket);
    }

    @Override
    public void cancelTicket(String ticketId){
        if(ticketHashMap.containsKey(ticketId)){
            ticketHashMap.remove(ticketId);
            System.out.println("Ticket cancelled successfully: "+ticketId);
        }else{
            System.out.println("Ticket not found: "+ticketId);
        }
    }

    @Override
    public Ticket getTicketById(String ticketId){
        return ticketHashMap.getOrDefault(ticketId,null);
    }

    @Override
    public String generateTicketId(String vehicleId,String parkingSpotId,LocalDateTime startTime){
        return vehicleId+"-"+parkingSpotId+"-"+startTime;
    }

    @Override
    public void updateTicket(Ticket ticket){
        if(ticketHashMap.containsKey(ticket.getTicketId())){
            ticketHashMap.put(ticket.getTicketId(),ticket);
            System.out.println("Ticket updated successfully: "+ticket);
            return ;
        }
        System.out.println("Ticket not found: "+ticket.getTicketId());

    }

    @Override
    public Ticket getTicketByVehicleId(String vehicleId){
        return ticketHashMap.values().stream()
                .filter(ticket -> ticket.getVehicleId().equals(vehicleId))
                .findFirst()
                .orElse(null);
    }
}
