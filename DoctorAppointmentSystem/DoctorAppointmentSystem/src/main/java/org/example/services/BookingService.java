package org.example.services;

import org.example.dto.DoctorSlot;
import org.example.enitities.Booking;
import org.example.enitities.Doctor;
import org.example.enitities.Patient;
import org.example.enitities.enums.BookingStatus;
import org.example.enitities.enums.Specialisation;
import org.example.exception.BookingNotFoundException;
import org.example.exception.DoctorNotFoundException;
import org.example.exception.PatientNotFoundException;
import org.example.repository.BookingRepository;
import org.example.repository.DoctorRepository;
import org.example.repository.PatientRepository;
import org.example.strategy.RankingStrategy;

import java.util.List;

public class BookingService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final RankingStrategy ratingStrategy;
    private final BookingRepository bookingRepository;

    public BookingService(PatientRepository patientRepository, DoctorRepository doctorRepository, RankingStrategy ratingStrategy, BookingRepository bookingRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.ratingStrategy = ratingStrategy;
        this.bookingRepository = bookingRepository;
    }

    public List<DoctorSlot>seachDoctor(Specialisation specialisation){
        List<DoctorSlot>doctorRanking=ratingStrategy.rankDoctors(specialisation);
        return doctorRanking;
    }

    public String saveAppointment(String patientId,String doctorId,String slot){
        Patient patient=patientRepository.getPatientById(patientId);
        if(patient==null){
            throw new PatientNotFoundException("Patient with id "+patientId+" not found");
        }
        Doctor doctor=doctorRepository.getDoctorById(doctorId);
        if(doctor==null){
            throw new DoctorNotFoundException("Doctor with id "+doctorId+" not found");
        }
        if(!doctor.getAvailableSlots().containsKey(slot)){
            throw new IllegalArgumentException("Slot "+slot+" is not available for doctor with id "+doctorId);
        }
        List<Booking>patientBookings=bookingRepository.getAllBookingsByPatientId(patientId);
        for(Booking booking:patientBookings){
            if(booking.getSlot().equals(slot)){
                throw new IllegalArgumentException("Patient with id "+patientId+" already has a booking at slot "+slot);
            }
        }
        if(doctor.getAvailableSlots().get(slot)==true){
            Booking booking =new Booking(slot,doctorId,patientId, BookingStatus.CONFIRMED);
            bookingRepository.saveBooking(booking.getBookingId(),booking);
            return booking.getBookingId();
        }

        if(doctor.getAvailableSlots().get(slot)==false){
            Booking booking =new Booking(slot,doctorId,patientId, BookingStatus.WAITLISTED);
            bookingRepository.saveBooking(booking.getBookingId(),booking);
            String doctorSlot=doctorId+"_"+slot;
            bookingRepository.addBookingToWaitList(doctorSlot,booking.getBookingId());
            return booking.getBookingId();
        }
        return null;
    }

    public String cancelAppointment(String bookingId){
        Booking booking=bookingRepository.getBookingById(bookingId);
        if(booking==null){
            throw new BookingNotFoundException("Booking with id "+bookingId+" not found");
        }
       bookingRepository.cancelBooking(bookingId);
        String doctorSlot=booking.getDoctorId()+"_"+booking.getSlot();
        String waitlistedBookingId=bookingRepository.removeBookingFromWaitlist(doctorSlot);
        if(waitlistedBookingId==null){
            return "Booking with id "+bookingId+" cancelled successfully";
        }
        Booking waitlistedBooking=bookingRepository.getBookingById(waitlistedBookingId);
        waitlistedBooking.setStatus(BookingStatus.CONFIRMED);
        bookingRepository.saveBooking(waitlistedBookingId,waitlistedBooking);
        return "Booking with id "+bookingId+" cancelled successfully and waitlisted booking with id "+waitlistedBookingId+" confirmed";
    }

    public List<String>getAllBookingsByPatientId(String patientId) {
        Patient patient = patientRepository.getPatientById(patientId);
        if (patient == null) {
            throw new PatientNotFoundException("Patient with id " + patientId + " not found");
        }
        return bookingRepository.getAllBookingsByPatientId(patientId).stream().map(Booking::getBookingId).toList();
    }

    public List<String>getAllBookingsByDoctorId(String doctorId) {
        Doctor doctor = doctorRepository.getDoctorById(doctorId);
        if (doctor == null) {
            throw new DoctorNotFoundException("Doctor with id " + doctorId + " not found");
        }
        return bookingRepository.getAllBookingsByDoctorId(doctorId).stream().map(Booking::getBookingId).toList();
    }
    public List<String>getAllBookings() {
        return bookingRepository.getAllBookings().stream().map(Booking::getBookingId).toList();
    }

}
