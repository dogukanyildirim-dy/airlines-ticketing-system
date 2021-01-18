package com.dogukanyildirim.airlinesticketingsystem.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class JourneyRequest {
    String sourceAirport;
    String destinationAirport;
    LocalDate flightDate;
    Boolean isOneWay;
    LocalDate returnFlightDate;
    Integer numberOfPassengers;
}
