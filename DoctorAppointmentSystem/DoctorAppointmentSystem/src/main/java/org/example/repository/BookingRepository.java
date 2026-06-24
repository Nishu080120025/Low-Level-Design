package org.example.repository;

import org.example.dto.DoctorSlot;
import org.example.enitities.Booking;
import org.example.enitities.enums.Specialisation;

import java.util.List;

public interface BookingRepository {
   Booking saveBooking(String bookingId, Booking booking);
    Booking cancelBooking(String bookingId);
    Booking getBookingById(String bookingId);
    List<Booking> getAllBookings();
    List<Booking> getAllBookingsByDoctorId(String doctorId);
    List<Booking>getAllBookingsByPatientId(String patientId);
    void addBookingToWaitList(String doctorSlot, String BookingId);
    String removeBookingFromWaitlist(String doctorSlot);

}
