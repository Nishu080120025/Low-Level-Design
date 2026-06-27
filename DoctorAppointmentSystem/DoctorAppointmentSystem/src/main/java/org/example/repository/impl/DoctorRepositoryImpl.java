package org.example.repository.impl;

import org.example.enitities.Doctor;
import org.example.exception.DoctorNotFoundException;
import org.example.repository.DoctorRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorRepositoryImpl implements DoctorRepository {
    private final HashMap<String, Doctor> doctorMap;

    public DoctorRepositoryImpl(HashMap<String, Doctor> doctorMap) {
        this.doctorMap = doctorMap;
    }

    @Override
    public Doctor registerDoctor(String doctorId, Doctor doctor) {
        doctorMap.put(doctorId, doctor);
        return doctor;
    }

    @Override
    public Doctor getDoctorById(String doctorId) {
        return doctorMap.get(doctorId);
    }

    @Override
    public void addSlotForAvailablity(String doctorId,List<String>slots){
        if(!doctorMap.containsKey(doctorId)){
            throw new DoctorNotFoundException("Doctor with ID " + doctorId + " not found.");
        }
        Doctor doctor= doctorMap.get(doctorId);
        Map<String, Boolean> availabilityMap=doctor.getAvailableSlots();
        slots.stream().filter(slot->availabilityMap.containsKey(slot)).forEach(slot->availabilityMap.put(slot,true));
    }

    @Override
    public List<Doctor>getDoctorBySpecialisation(org.example.enitities.enums.Specialisation specialisation){
        return doctorMap.values().stream().filter(doctor->doctor.getSpecialisation().equals(specialisation)).toList();
    }

}
