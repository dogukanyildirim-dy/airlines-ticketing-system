package com.dogukanyildirim.airlinesticketingsystem.service;

import com.dogukanyildirim.airlinesticketingsystem.domain.Route;

import java.util.List;

public interface RouteService {
    Route create(Route route);

    Route read(Integer id);

    List<Route> readAll();

    Route update(Route route);

    Route delete(Integer id);
}
