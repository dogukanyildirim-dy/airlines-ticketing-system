package com.dogukanyildirim.airlinesticketingsystem.dao;

import com.dogukanyildirim.airlinesticketingsystem.domain.management.Flight;
import com.dogukanyildirim.airlinesticketingsystem.domain.management.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    boolean existsByFlightCodeAndFlightDate(String flightCode, LocalDate flightDate);
    List<Flight> findAllByRouteAndFlightDateOrderByDepartureTimeAsc(Route route, LocalDate flightDate);
}
