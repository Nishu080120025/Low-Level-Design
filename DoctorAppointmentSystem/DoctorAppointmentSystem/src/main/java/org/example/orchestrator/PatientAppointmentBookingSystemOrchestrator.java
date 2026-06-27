package org.example.orchestrator;

import org.example.dto.DoctorSlot;
import org.example.enitities.enums.Specialisation;
import org.example.services.BookingService;
import org.example.services.DoctorService;
import org.example.services.PatientService;

import java.util.List;
import java.util.Map;

public class PatientAppointmentBookingSystemOrchestrator {


    private final BookingService bookingService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    public PatientAppointmentBookingSystemOrchestrator( BookingService bookingService, DoctorService doctorService, PatientService patientService) {
        this.bookingService = bookingService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    public String registerPatient(String name) {
        String patientId=patientService.registerPatient(name);
        System.out.println("Patient registered successfully with name "+name);
        return patientId;
    }

    public String registerDoctor(String name, Specialisation specialisation, Map<String, Boolean> availablity, Double rating) {
        String doctorId=doctorService.registerDoctor(name, specialisation, rating, availablity);
       System.out.println("Doctor registered successfully with specialisation "+specialisation+" and rating "+rating);
       return doctorId;
    }

    public void markDoctorAvailablity(String doctorId, List<String> slots){
        doctorService.addAvailablity(doctorId,slots);
    }

    public List<DoctorSlot>searchDoctorsBasedOnSpecialisation(Specialisation specialisation){
        return bookingService.seachDoctor(specialisation);
    }

    public String bookAppointment(String patientId,String doctorId,String slot){

        String bookingId= bookingService.saveAppointment(patientId,doctorId,slot);
        System.out.println("Appointment booked successfully with booking id "+bookingId);
        return bookingId;
    }

    public String cancelAppointment(String bookingId){
        return bookingService.cancelAppointment(bookingId);
    }

    public List<String>findAllTheBookingsByThePatient(String patientId){
        return bookingService.getAllBookingsByPatientId(patientId);
    }

    public List<String>findAllTheBookingsByTheDoctor(String doctorId){
        return bookingService.getAllBookingsByDoctorId(doctorId);
    }
}
