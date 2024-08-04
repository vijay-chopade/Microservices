package com.hospital.doctor.repository;

import com.hospital.doctor.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findAll();

    //    @Query("SELECT p FROM Doctort p JOIN FETCH p.address")
    @Query("SELECT DISTINCT p FROM Doctor p JOIN FETCH p.address")
    List<Doctor> findAllDoctorsWithAddresses();

//    @Query("SELECT a, p FROM Address a LEFT JOIN a.doctor p WHERE p.email = :email")
//    List<Object[]> findDoctorByEmail(@Param("email") String email);

    @Query("SELECT p.email,p.password FROM Patient p WHERE p.email = :email")
    Object findDoctorByEmail(@Param("email") String email);

}
