package org.example.repository.impl;

import org.example.enitities.Booking;
import org.example.exception.BookingNotFoundException;
import org.example.repository.BookingRepository;

import java.util.*;

public class BookingRepositoryImpl implements BookingRepository {
    private final HashMap<String, Booking> bookingMap;
    private final Map<String, Queue<String>> waitingListMap;

    public BookingRepositoryImpl(HashMap<String, Booking> bookingMap, Map<String, Queue<String>> waitingListMap) {
        this.bookingMap = bookingMap;
        this.waitingListMap = waitingListMap;
    }

    @Override
    public Booking saveBooking(String bookingId, Booking booking) {
        bookingMap.put(bookingId, booking);
        return booking;
    }

    @Override
    public Booking cancelBooking(String bookingId) {
        if (!bookingMap.containsKey(bookingId)) {
            throw new BookingNotFoundException("Booking with ID " + bookingId + " not found.");
        }
        return bookingMap.remove(bookingId);
    }

    @Override
    public Booking getBookingById(String bookingId) {
        return bookingMap.getOrDefault(bookingId, null);
    }

    @Override
    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookingMap.values());
    }

    @Override
    public List<Booking> getAllBookingsByDoctorId(String doctorId) {
        List<Booking> bookingListByDoctorId = new ArrayList<>();
        for (Booking booking : bookingMap.values()) {
            if (booking.getDoctorId().equals(doctorId)) {
                bookingListByDoctorId.add(booking);
            }
        }
        return bookingListByDoctorId;
    }

    @Override
    public List<Booking> getAllBookingsByPatientId(String patientId) {
        List<Booking> bookingListByPatientId = new ArrayList<>();
        for (Booking booking : bookingMap.values()) {
            if (booking.getPatientId().equals(patientId)) {
                bookingListByPatientId.add(booking);
            }
        }
        return bookingListByPatientId;
    }

    @Override
    public void addBookingToWaitList(String doctorSlot, String bookingId) {
        waitingListMap.putIfAbsent(doctorSlot, new LinkedList<>());
        waitingListMap.get(doctorSlot).add(bookingId);
    }

    @Override
    public String removeBookingFromWaitlist(String doctorSlot) {
        if (waitingListMap.containsKey(doctorSlot) && !waitingListMap.get(doctorSlot).isEmpty()) {
            return waitingListMap.get(doctorSlot).poll();
        }
        return null;
    }

}
