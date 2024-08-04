package com.hospital.patient.repository;

import com.hospital.patient.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findAll();

    //    @Query("SELECT p FROM Patient p JOIN FETCH p.address")
    @Query("SELECT DISTINCT p FROM Patient p JOIN FETCH p.address")
    List<Patient> findAllPatientsWithAddresses();

    @Query("SELECT a, p FROM Address a LEFT JOIN a.patient p WHERE p.email = :email")
    List<Object[]> findPatientByEmail(@Param("email") String email);

}
