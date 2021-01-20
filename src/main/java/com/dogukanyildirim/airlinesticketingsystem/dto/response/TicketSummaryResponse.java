package com.dogukanyildirim.airlinesticketingsystem.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class TicketSummaryResponse {
    private String pnrCode;
    private String airlineCompany;
    private String flightCode;
    private LocalDate flightDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private String sourceAirport;
    private String destinationAirport;
    private Float baggage;
    private Float cabinBaggage;
    private List<PassengerResponse> passengers;
    private String creditCardNo;
    private float payment;
}
