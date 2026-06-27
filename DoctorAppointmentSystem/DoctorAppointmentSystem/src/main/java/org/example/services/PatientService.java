package org.example.services;

import org.example.enitities.Patient;
import org.example.repository.PatientRepository;
import org.example.util.IdUtil;

public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public String registerPatient(String name){
        String patientId= IdUtil.generateId();
        Patient patient=new Patient(patientId,name);
         patientRepository.registerPatient(patientId,patient);
         return patientId;
    }

    public Patient getPatientById(String patientId){
        return patientRepository.getPatientById(patientId);
    }
}
