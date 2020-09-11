package com.ftx.saysomthing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ftx")
public class SaysomthingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaysomthingApplication.class, args);
    }

}
