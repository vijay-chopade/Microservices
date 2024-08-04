package com.hospital.doctor.entity;

import lombok.Data;

@Data
public class DoctorWithAddressDTO {
    private Long patientId;
    private String firstname;
    private String lastname;
    private String dateOfBirth;  // set value in Date format >>
    private String gender;
    private String phone;
    private String email;
    private String password;
    private String confirmPassword;

    private String street;
    private String city;
    private String state;
    private int zipcode;
    private String country;

}
