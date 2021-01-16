package com.dogukanyildirim.airlinesticketingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AirlineTicketingSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(AirlineTicketingSystemApplication.class, args);
    }
}
