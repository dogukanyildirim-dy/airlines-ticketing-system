package com.dogukanyildirim.airlinesticketingsystem.service;

import com.dogukanyildirim.airlinesticketingsystem.domain.passenger.Passenger;

import java.util.List;

public interface PassengerService {
    Passenger create(Passenger passenger);

    Passenger read(Integer id);

    List<Passenger> readAll();

    Passenger update(Passenger passenger);

    Passenger delete(Integer id);
}
