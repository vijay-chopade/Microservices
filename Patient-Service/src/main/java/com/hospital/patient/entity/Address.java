package com.hospital.patient.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "p_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "id")
    private Patient patient;
    private String street;
    private String city;
    private String state;
    private int zipcode;
    private String country;

}
