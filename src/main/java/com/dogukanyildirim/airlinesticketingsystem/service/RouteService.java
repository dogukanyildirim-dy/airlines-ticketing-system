package com.dogukanyildirim.airlinesticketingsystem.service;

import com.dogukanyildirim.airlinesticketingsystem.domain.management.Route;

import java.util.List;

public interface RouteService {
    Route create(Route route);

    Route read(Integer id);

    Route readBySourceIataAndDestionationIata(String sIata, String dIata);

    List<Route> readBySourceIata(String sIata);

    List<Route> readAll();

    Route update(Route route);

    Route delete(Integer id);
}
