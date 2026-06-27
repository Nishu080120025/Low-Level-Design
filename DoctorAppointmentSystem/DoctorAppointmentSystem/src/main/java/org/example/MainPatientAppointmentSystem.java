package org.example;

import org.example.dto.DoctorSlot;
import org.example.enitities.Patient;
import org.example.enitities.enums.Specialisation;
import org.example.orchestrator.PatientAppointmentBookingSystemOrchestrator;
import org.example.repository.BookingRepository;
import org.example.repository.DoctorRepository;
import org.example.repository.PatientRepository;
import org.example.repository.impl.BookingRepositoryImpl;
import org.example.repository.impl.DoctorRepositoryImpl;
import org.example.repository.impl.PatientRepositoryImpl;
import org.example.services.BookingService;
import org.example.services.DoctorService;
import org.example.services.PatientService;
import org.example.strategy.RankingStrategy;
import org.example.strategy.RatingBasedRankingStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainPatientAppointmentSystem {
    public static void main(String[] args) {
        PatientRepository patientRepository = new PatientRepositoryImpl(new HashMap<>());
        BookingRepository bookingRepository = new BookingRepositoryImpl(new HashMap<>(), new HashMap<>());
        DoctorRepository doctorRepository = new DoctorRepositoryImpl(new HashMap<>());
        RankingStrategy rankingStrategy = new RatingBasedRankingStrategy(doctorRepository);
        BookingService bookingService = new BookingService(patientRepository, doctorRepository, rankingStrategy, bookingRepository);
        PatientService patientService = new PatientService(patientRepository);
        DoctorService doctorService = new DoctorService(doctorRepository);
        PatientAppointmentBookingSystemOrchestrator patientAppointmentBookingSystemOrchestrator = new PatientAppointmentBookingSystemOrchestrator(bookingService, doctorService, patientService);

        String patient1 = patientAppointmentBookingSystemOrchestrator.registerPatient("John Doe");
        String patient2 = patientAppointmentBookingSystemOrchestrator.registerPatient("Jane Smith");

        String doctor1 = patientAppointmentBookingSystemOrchestrator.registerDoctor("Dr.Smith", Specialisation.CARDIOLOGY, Map.of("9:00-10:00", false, "10:30-11:30", false, "12:00-13:00", false), 4.2);

        String doctor2 = patientAppointmentBookingSystemOrchestrator.registerDoctor("Dr.Jone", Specialisation.PEDIATRICS, Map.of("9:30-10:30", false, "11:30-12:30", false, "14:00-15:00", false), 4.5);

        String doctor3 = patientAppointmentBookingSystemOrchestrator.registerDoctor("Dr.William", Specialisation.CARDIOLOGY, Map.of("9:20-10:20", false, "12:00-13:00", false, "15:00-16:00", false), 4.1);

        List<DoctorSlot> doctorsListBasedOnRanking = patientAppointmentBookingSystemOrchestrator.searchDoctorsBasedOnSpecialisation(Specialisation.CARDIOLOGY);

        patientAppointmentBookingSystemOrchestrator.markDoctorAvailablity(doctor1, List.of("9:00-10:00", "12:00-13:00"));

        String booking1 = patientAppointmentBookingSystemOrchestrator.bookAppointment(patient1, doctor1, "9:00-10:00");
        String booking2 = patientAppointmentBookingSystemOrchestrator.bookAppointment(patient2, doctor1, "9:00-10:00");
        String booking3 = patientAppointmentBookingSystemOrchestrator.bookAppointment(patient2, doctor2, "11:30-12:30");

        String canceledBooking = patientAppointmentBookingSystemOrchestrator.cancelAppointment(booking1);

        List<String> appointmentListOfPatient1 = patientAppointmentBookingSystemOrchestrator.findAllTheBookingsByThePatient(patient1);
        System.out.println("Appointment list of patient " + patient1 + " : " + appointmentListOfPatient1);
        List<String> appointmentListOfPatient2 = patientAppointmentBookingSystemOrchestrator.findAllTheBookingsByThePatient(patient2);
        System.out.println("Appointment list of patient " + patient2 + " : " + appointmentListOfPatient2);
        List<String> appointmentListOfDoctor = patientAppointmentBookingSystemOrchestrator.findAllTheBookingsByTheDoctor(doctor1);
        System.out.println("Appointment list of doctor " + doctor1 + " : " + appointmentListOfDoctor);
    }
}