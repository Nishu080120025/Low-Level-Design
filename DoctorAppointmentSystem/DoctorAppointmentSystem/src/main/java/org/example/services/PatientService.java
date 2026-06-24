package org.example.services;

import org.example.enitities.Patient;
import org.example.repository.PatientRepository;
import org.example.util.IdUtil;

public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient registerPatient(String name){
        String patientId= IdUtil.generateId();
        Patient patient=new Patient(patientId,name);
        return patientRepository.registerPatient(patientId,patient);
    }

    public Patient getPatientById(String patientId){
        return patientRepository.getPatientById(patientId);
    }
}
