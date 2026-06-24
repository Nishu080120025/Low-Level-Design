package org.example.services;

import org.example.enitities.Doctor;
import org.example.enitities.enums.Specialisation;
import org.example.repository.DoctorRepository;
import org.example.util.IdUtil;

import java.util.HashMap;
import java.util.List;

public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor registerDoctor(String doctorName, Specialisation specialisation, Double Rating, HashMap<String, Boolean> availablityMap){
        Doctor doctor=new Doctor(doctorName,specialisation,Rating,availablityMap);
        return doctorRepository.registerDoctor(doctor.getId(),doctor);
    }

    public void addAvailablity(String doctorId, List<String> slots){
        doctorRepository.addSlotForAvailablity(doctorId,slots);
    }

}
