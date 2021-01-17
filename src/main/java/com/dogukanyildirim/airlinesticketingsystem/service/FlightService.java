package com.dogukanyildirim.airlinesticketingsystem.service;

import com.dogukanyildirim.airlinesticketingsystem.domain.management.Flight;

import java.util.List;

public interface FlightService {
    Flight create(Flight flight);

    Flight read(Integer id);

    List<Flight> readAll();

    Flight update(Flight flight);

    Flight delete(Integer id);
}
