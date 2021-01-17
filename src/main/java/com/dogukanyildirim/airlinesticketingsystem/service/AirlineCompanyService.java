package com.dogukanyildirim.airlinesticketingsystem.service;

import com.dogukanyildirim.airlinesticketingsystem.domain.management.AirlineCompany;

import java.util.List;

public interface AirlineCompanyService {
    AirlineCompany create(AirlineCompany airlineCompany);

    AirlineCompany read(Integer id);

    List<AirlineCompany> readAll();

    AirlineCompany update(AirlineCompany airlineCompany);

    AirlineCompany delete(Integer id);
}
