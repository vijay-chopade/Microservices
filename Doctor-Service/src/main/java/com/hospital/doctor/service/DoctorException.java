package com.hospital.doctor.service;

import lombok.Data;

@Data
public class DoctorException extends RuntimeException {

    public DoctorException(String s) {
    }
    public DoctorException() {
        super("Doctor object cannot be null");
    }
}
