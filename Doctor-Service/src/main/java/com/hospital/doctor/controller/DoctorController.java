package com.hospital.doctor.controller;

import com.hospital.doctor.entity.Doctor;
import com.hospital.doctor.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/doctor")//"/api"
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @GetMapping("/")
    public String welcome() {
        doctorService.welcome();
        return "<h1>Welcome to the Doctor APi</h1>";
    }

    @GetMapping("/doctors")
    public List<Doctor> getDoctors() {
        return doctorService.getAllDoctor();
    }

    @GetMapping("/doctor/all-with-addresses")
    public ResponseEntity<List<Doctor>> getAllDoctorsWithAddresses() {
        List<Doctor> doctorsWithAddresses = doctorService.getAllDoctorsWithAddresses();
        return new ResponseEntity<>(doctorsWithAddresses, HttpStatus.OK);
    }

    @PostMapping("/doctor")
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorService.createDoctor(doctor);
    }

    @GetMapping("/doctor/{id}")
    public Doctor getDoctor(@PathVariable("id") Long id) {
        return doctorService.getDoctorById(id);
    }

    @PutMapping("/doctor/{id}")
    public String UpdateDoctor(@PathVariable("doctorId")Long doctorId,@RequestBody Doctor doctor) {
        return doctorService.updateDoctor(doctorId,doctor);
    }

    @DeleteMapping("/doctor/{id}")
    public Doctor removeDoctor(@PathVariable("id") Long id) {
        return doctorService.deleteDoctor(id);
    }

    @GetMapping("/login")
    public Map<String,String> getLogin(HttpRequest req){
        String email = String.valueOf(req.getHeaders().get("email"));
        return doctorService.getDoctorIdAndPassword(email);
    }
}
