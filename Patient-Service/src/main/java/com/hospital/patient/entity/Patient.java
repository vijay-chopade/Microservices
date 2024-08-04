package com.hospital.patient.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @JsonManagedReference
    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private Address address;

    @Column(unique = true, nullable = false)
    private String PatientId;

    @JsonProperty("f_name")
    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @DateTimeFormat(pattern = "MM-DD-YYYY")
    @Column(name = "date_of_birth",nullable = false)
    private String dateOfBirth;

    @Column(name = "gender",nullable = false)
    private String gender;

    @Column(name = "phone",unique = true)
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
