package com.hospital.login.entity;

import lombok.Data;

@Data
public class Login {
    private Long userId;
    private String password;
    private String role;
}
