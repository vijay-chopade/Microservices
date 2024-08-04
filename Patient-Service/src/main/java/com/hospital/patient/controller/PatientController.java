package com.hospital.patient.controller;

import com.hospital.patient.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hospital.patient.service.PatientServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/patient")//"/api"
public class PatientController {

    @Autowired
    PatientServiceImpl patientService;

    @GetMapping("/")
    public String welcome() {
        patientService.welcome();
        return "<h1>Welcome to the Patient APi</h1>";
    }

    @GetMapping("/patients")
    public List<Patient> getPatients() {
        return patientService.getAllPatient();
    }

    @GetMapping("/all-with-addresses")
    public ResponseEntity<List<Patient>> getAllPatientsWithAddresses() {
        List<Patient> patientsWithAddresses = patientService.getAllPatientsWithAddresses();
        return new ResponseEntity<>(patientsWithAddresses, HttpStatus.OK);
    }

    @PostMapping("/patient")
    public String createPatient(@RequestBody Patient patient) {
        return patientService.createPatient(patient);
    }

    @GetMapping("/patient/{id}")
    public Patient getPatient(@PathVariable("id") Long id) {
        return patientService.getPatientById(id);
    }

    @PutMapping("/patient")
    public String UpdatePatient(@PathVariable("id") Long id,@RequestBody Patient patient) {
        return patientService.updatePatient(id,patient);
    }

    @DeleteMapping("/patient/{id}")
    public Patient removePatient(@PathVariable("id") Long id) {
        return patientService.deletePatient(id);
    }
}
