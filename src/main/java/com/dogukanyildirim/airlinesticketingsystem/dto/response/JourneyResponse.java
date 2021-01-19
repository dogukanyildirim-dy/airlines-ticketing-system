package com.dogukanyildirim.airlinesticketingsystem.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class JourneyResponse {
    private Integer flightId;
    private String flightCode;
    private LocalDate flightDate;
    private LocalTime departureTime;
    private LocalTime duration;
    private LocalTime arrivalTime;
    private Integer routeId;
    private String routeSource;
    private String routeDestination;
    private Integer airlineCompanyId;
    private String airlineCompany;
    private String flightType;
    private List<JourneyFlightPackageResponse> journeyFlightPackages;
}
