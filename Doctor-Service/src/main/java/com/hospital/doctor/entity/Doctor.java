package com.hospital.doctor.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

@Data
@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String DrId;

    @OneToMany(targetEntity = Expert.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id")
    private List<Expert> experts;

    @JsonManagedReference
    @OneToOne(mappedBy = "doctor", cascade = CascadeType.ALL)
    private Address address;

    @JsonProperty("f_name")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @DateTimeFormat(pattern = "MM-DD-YYYY")
    @Column(name = "date_of_birth", nullable = false)
    private String dateOfBirth;  // set value in Date format >>

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "phone", unique = true)
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Email format is invalid")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    @Pattern(regexp = "^.*(?=.{8,15})(?=.*\\d)(?=.*[a-zA-Z])|(?=.{8,15})(?=.*\\d)(?=.*[!@#$%^&])|(?=.{8,15})(?=.*[a-zA-Z])(?=.*[!@#$%^&]).*$")
    private String password;

    @NotBlank(message = "Confirm Password is mandatory")
    @Column(name = "confirm_password", nullable = false)
    private String confirmPassword;
}
