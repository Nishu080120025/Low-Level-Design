package org.example.entities;

import org.example.entities.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket {
    private String ticketId;
    private String vehicleId;
    private String parkingSpotId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private PaymentStatus paymentStatus;

    public Ticket(String ticketId, String vehicleId, String parkingSpotId, LocalDateTime entryTime) {
        this.ticketId = ticketId;
        this.vehicleId = vehicleId;
        this.parkingSpotId = parkingSpotId;
        this.entryTime = entryTime;
        this.exitTime = LocalDateTime.now();
        this.paymentStatus = PaymentStatus.PENDING;
    }

    public String getTicketId() {
        return ticketId;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getParkingSpotId() {
        return parkingSpotId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Ticket ticket)) return false;
        return Objects.equals(ticketId, ticket.ticketId) && Objects.equals(vehicleId, ticket.vehicleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, vehicleId);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", parkingSpotId='" + parkingSpotId + '\'' +
                ", entryTime=" + entryTime +
                ", exitTime=" + exitTime +
                '}';
    }
}
