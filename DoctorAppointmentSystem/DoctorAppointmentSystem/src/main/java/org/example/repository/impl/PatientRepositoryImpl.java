package org.example.repository.impl;

import org.example.enitities.Patient;
import org.example.repository.PatientRepository;

import java.util.Map;

public class PatientRepositoryImpl implements PatientRepository {
    private final Map<String, Patient> patientMap;
    public PatientRepositoryImpl(Map<String, Patient> patientMap){
        this.patientMap=patientMap;
    }

    @Override
    public Patient registerPatient(String patientId, Patient patient) {
        patientMap.put(patientId, patient);
        return patient;
    }

    @Override
    public Patient getPatientById(String patientId) {
        return patientMap.get(patientId);
    }

}
