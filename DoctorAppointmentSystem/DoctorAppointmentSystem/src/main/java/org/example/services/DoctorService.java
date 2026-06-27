package org.example.services;

import org.example.enitities.Doctor;
import org.example.enitities.enums.Specialisation;
import org.example.repository.DoctorRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public String registerDoctor(String doctorName, Specialisation specialisation, Double Rating, Map<String, Boolean> availablityMap){
        Map<String, Boolean> mutableAvailability = new HashMap<>(availablityMap);
        Doctor doctor=new Doctor(doctorName,specialisation,Rating,mutableAvailability);
      doctorRepository.registerDoctor(doctor.getId(),doctor);
      return doctor.getId();
    }

    public void addAvailablity(String doctorId, List<String> slots){
        doctorRepository.addSlotForAvailablity(doctorId,slots);
    }

}
