package com.hospital.doctor.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "doctor_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "id")
    private Doctor doctor;
    private String street;
    private String city;
    private String state;
    private int zipcode;
    private String country;

}
