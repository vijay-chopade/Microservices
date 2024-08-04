package com.hospital.patient.service;

import com.hospital.patient.entity.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {

    public String welcome();

    public List<Patient> getAllPatient();

    public List<Patient> getAllPatientsWithAddresses() ;

    public String createPatient(Patient patient);

    public Patient getPatientById(Long id);

    public String updatePatient(Long patientId, Patient updatedPatient);

    public Patient deletePatient(Long id) ;

}
