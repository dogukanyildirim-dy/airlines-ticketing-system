package com.dogukanyildirim.airlinesticketingsystem.dao;

import com.dogukanyildirim.airlinesticketingsystem.domain.management.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    boolean existsByFlightCodeAndFlightDate(String flightCode, LocalDate flightDate);
}
