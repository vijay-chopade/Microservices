package com.hospital.patient.service;

import ch.qos.logback.classic.Logger;
import com.hospital.patient.entity.Patient;
import com.hospital.patient.repository.AddressRepository;
import com.hospital.patient.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService {

    Logger logger = (Logger) LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    SessionFactory factory;

    public String welcome() {
        logger.info("Service class runing....1");
        logger.error("Service class runing....3");
        return "Runing.......................................";
    }

    public List<Patient> getAllPatient() {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Patient> patientList = patientRepository.findAll();
        transaction.commit();
        session.close();
        return patientList;
    }

    public List<Patient> getAllPatientsWithAddresses() {
        return patientRepository.findAllPatientsWithAddresses();
    }

    public String createPatient(Patient patient) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        patient.setPatientId(getNewId());
        Patient p = patientRepository.save(patient);
        transaction.commit();
        session.close();
        return "Patient Data added Successfully";
    }

    public Patient getPatientById(Long id) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Patient patient = patientRepository.findById(id).get();
        transaction.commit();
        session.close();
        return patient;
    }

    @Transactional
    public String updatePatient(Long patientId, Patient updatedPatient) {
        try {

            if (updatedPatient.getPassword().equals(updatedPatient.getConfirmPassword())) {
                Patient existingDoctor = patientRepository.findById(patientId).orElseThrow(() -> new PatientException("Doctor not found with id: " + patientId));

                if (isNullOrBlank(updatedPatient.getAddress())) existingDoctor.setAddress(updatedPatient.getAddress());

                if (isNullOrBlank(updatedPatient.getFirstName()))
                    existingDoctor.setFirstName(updatedPatient.getFirstName());

                if (isNullOrBlank(updatedPatient.getLastName()))
                    existingDoctor.setLastName(updatedPatient.getLastName());

                if (isNullOrBlank(updatedPatient.getDateOfBirth()))
                    existingDoctor.setDateOfBirth(updatedPatient.getDateOfBirth());

                if (isNullOrBlank(updatedPatient.getGender())) existingDoctor.setGender(updatedPatient.getGender());

                if (isNullOrBlank(updatedPatient.getPhone())) existingDoctor.setPhone(updatedPatient.getPhone());

                if (isNullOrBlank(updatedPatient.getEmail())) existingDoctor.setEmail(updatedPatient.getEmail());

                if (isNullOrBlank(updatedPatient.getPassword()))
                    existingDoctor.setPassword(updatedPatient.getPassword());

                if (isNullOrBlank(updatedPatient.getConfirmPassword()))
                    existingDoctor.setConfirmPassword(updatedPatient.getConfirmPassword());
                patientRepository.save(existingDoctor);
                return "Doctor Data Updated Successfully";
            } else throw new PatientException();
        } catch (PatientException e) {
            return "Exception caught: " + e.getMessage();
        }
    }

    public Patient deletePatient(Long id) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Patient patient = patientRepository.findById(id).get();
        patientRepository.delete(patient);
        transaction.commit();
        session.close();
        return patient;
    }

    private String getNewId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    private boolean isNotNull(Object obj) {
        return obj != null;
    }

    private boolean isNullOrBlank(Object object) {
        if (object == null) {
            return true;
        } else if (object instanceof String) {
            return ((String) object).trim().isEmpty();
        } else {
            return true;
        }
    }
}
