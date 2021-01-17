package com.dogukanyildirim.airlinesticketingsystem.dto;

import com.dogukanyildirim.airlinesticketingsystem.dto.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
public class FlightDTO extends BaseDTO {
    private String flightCode;
    private RouteDTO route;
    private AirlineCompanyDTO airlineCompany;
    private AirplaneDTO airplane;
    private Boolean isDirectly;
    private LocalDate flightDate;
    private LocalTime departureTime;
    private LocalTime duration;
    private Set<FlightPackageDTO> flightPackages;
}
