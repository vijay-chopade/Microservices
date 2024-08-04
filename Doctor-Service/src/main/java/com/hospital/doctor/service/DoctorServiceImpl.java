package com.hospital.doctor.service;

import ch.qos.logback.classic.Logger;
import com.hospital.doctor.entity.Doctor;
import com.hospital.doctor.repository.AddressRepository;
import com.hospital.doctor.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class DoctorServiceImpl implements DoctorService {

    Logger logger = (Logger) LoggerFactory.getLogger(DoctorServiceImpl.class);

    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    SessionFactory factory;
    @Autowired
    CommonFields commonFields;

    public String welcome() {
        logger.info("Service class runing....1");
        logger.error("Service class runing....3");
        return "Runing.......................................";
    }


    public List<Doctor> getAllDoctor() {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Doctor> doctorList = doctorRepository.findAll();
        transaction.commit();
        session.close();
        return doctorList;
    }

    public List<Doctor> getAllDoctorsWithAddresses() {
        return doctorRepository.findAllDoctorsWithAddresses();
    }

    @Transactional
    public Doctor createDoctor(Doctor doctor) {
        try {
            doctor.setDrId(getNewId());
            Doctor d = doctorRepository.save(doctor);
            doctorRepository.save(doctor);
        return d;
        }catch (Exception e) {
            throw new DoctorException();
        }
    }

    public Doctor getDoctorById(Long id) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Doctor doctor = doctorRepository.findById(id).get();
        transaction.commit();
        session.close();
        return doctor;
    }

    @Transactional
    public String updateDoctor(Long doctorId, Doctor updatedDoctor) {
        try {

            if (updatedDoctor.getPassword().equals(updatedDoctor.getConfirmPassword())) {
                Doctor existingDoctor = doctorRepository.findById(doctorId)
                        .orElseThrow(() -> new DoctorException("Doctor not found with id: " + doctorId));
                if (isNullOrBlank(updatedDoctor.getExperts()))
                    existingDoctor.setExperts(updatedDoctor.getExperts());

                if (isNullOrBlank(updatedDoctor.getAddress()))
                    existingDoctor.setAddress(updatedDoctor.getAddress());

                if (isNullOrBlank(updatedDoctor.getFirstName()))
                    existingDoctor.setFirstName(updatedDoctor.getFirstName());

                if (isNullOrBlank(updatedDoctor.getLastName()))
                    existingDoctor.setLastName(updatedDoctor.getLastName());

                if (isNullOrBlank(updatedDoctor.getDateOfBirth()))
                    existingDoctor.setDateOfBirth(updatedDoctor.getDateOfBirth());

                if (isNullOrBlank(updatedDoctor.getGender()))
                    existingDoctor.setGender(updatedDoctor.getGender());

                if (isNullOrBlank(updatedDoctor.getPhone()))
                    existingDoctor.setPhone(updatedDoctor.getPhone());

                if (isNullOrBlank(updatedDoctor.getEmail()))
                    existingDoctor.setEmail(updatedDoctor.getEmail());

                if (isNullOrBlank(updatedDoctor.getPassword()))
                    existingDoctor.setPassword(updatedDoctor.getPassword());

                if (isNullOrBlank(updatedDoctor.getConfirmPassword()))
                    existingDoctor.setConfirmPassword(updatedDoctor.getConfirmPassword());
                doctorRepository.save(existingDoctor);
                return "Doctor Data Updated Successfully";
            } else
                throw new DoctorException();
        }catch (DoctorException e){
            return "Exception caught: " + e.getMessage();
        }
    }

    public Doctor deleteDoctor(Long id) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Doctor doctor = doctorRepository.findById(id).get();
        doctorRepository.delete(doctor);
        transaction.commit();
        session.close();
        return doctor;
    }

    @Override
    public Map<String, String> getDoctorIdAndPassword(String email) {
        Object object = doctorRepository.findDoctorByEmail(email);
//        String.valueOf(email);
        Map<String,String> map = new HashMap<>();
        map.put(email,email);
        return map;
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
