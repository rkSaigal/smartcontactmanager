package com.smart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

//@EnableWebSecurity
@SpringBootApplication
public class SmartcontactmanagerApplication {



    public static void main(String[] args) {
        SpringApplication.run(SmartcontactmanagerApplication.class, args);
    }

}
