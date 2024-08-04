package com.hospital.doctor.service;

import com.hospital.doctor.entity.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface DoctorService {

    public String welcome();

    public List<Doctor> getAllDoctor();

    public List<Doctor> getAllDoctorsWithAddresses();

    public Doctor createDoctor(Doctor doctor);

    public Doctor getDoctorById(Long id);

    public String updateDoctor(Long doctorId,Doctor doctor);

    public Doctor deleteDoctor(Long id);

    public Map<String,String> getDoctorIdAndPassword(String email);

}
