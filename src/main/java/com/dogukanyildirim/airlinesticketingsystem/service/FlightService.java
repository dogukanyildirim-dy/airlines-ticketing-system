package com.dogukanyildirim.airlinesticketingsystem.service;

import com.dogukanyildirim.airlinesticketingsystem.domain.management.Flight;
import com.dogukanyildirim.airlinesticketingsystem.domain.management.Route;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {
    Flight create(Flight flight);

    Flight read(Integer id);

    List<Flight> readByRouteAndFlightDate(Route route, LocalDate localDate);

    List<Flight> readAll();

    Flight update(Flight flight);

    Flight delete(Integer id);
}
