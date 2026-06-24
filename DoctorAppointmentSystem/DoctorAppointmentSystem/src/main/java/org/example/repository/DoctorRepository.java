package org.example.repository;


import org.example.enitities.Doctor;
import org.example.enitities.enums.Specialisation;

import java.util.List;

public interface DoctorRepository {
    Doctor registerDoctor(String doctorId, Doctor doctor);
    Doctor getDoctorById(String doctorId);
    void addSlotForAvailablity(String doctorId, List<String> slots);
    List<Doctor>getDoctorBySpecialisation(Specialisation specialisation);
}
