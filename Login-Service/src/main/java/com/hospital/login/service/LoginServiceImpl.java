package com.hospital.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public String getLogin() {
        //working on it
//        restTemplate.getForObject();
        return null;
    }
}
