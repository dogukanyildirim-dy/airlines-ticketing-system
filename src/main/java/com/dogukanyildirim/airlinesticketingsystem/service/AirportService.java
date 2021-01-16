package com.dogukanyildirim.airlinesticketingsystem.service;

import com.dogukanyildirim.airlinesticketingsystem.domain.Airport;

import java.util.List;

public interface AirportService {
    Airport create(Airport airport);

    Airport read(Integer id);

    List<Airport> readAll();

    Airport update(Airport airport);

    Airport delete(Integer id);
}
