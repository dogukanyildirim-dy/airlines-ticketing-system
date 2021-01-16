package com.dogukanyildirim.airlinesticketingsystem.service;

import com.dogukanyildirim.airlinesticketingsystem.domain.Airplane;

import java.util.List;

public interface AirplaneService {
    Airplane create(Airplane airplane);

    Airplane read(Integer id);

    List<Airplane> readAll();

    Airplane update(Airplane airplane);

    Airplane delete(Integer id);
}
