package org.example.repository;

import org.example.enitities.Patient;

public interface PatientRepository {

    Patient registerPatient(String patientId, Patient patient);
    Patient getPatientById(String patientId);
}
