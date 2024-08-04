package com.hospital.doctor.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Expert {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String expertise;


}
