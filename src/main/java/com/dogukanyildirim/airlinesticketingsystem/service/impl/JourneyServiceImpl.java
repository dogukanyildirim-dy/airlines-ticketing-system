package com.dogukanyildirim.airlinesticketingsystem.service.impl;

import com.dogukanyildirim.airlinesticketingsystem.domain.management.Flight;
import com.dogukanyildirim.airlinesticketingsystem.domain.management.Route;
import com.dogukanyildirim.airlinesticketingsystem.dto.request.JourneyRequest;
import com.dogukanyildirim.airlinesticketingsystem.dto.response.JourneyResponse;
import com.dogukanyildirim.airlinesticketingsystem.service.FlightService;
import com.dogukanyildirim.airlinesticketingsystem.service.JourneyService;
import com.dogukanyildirim.airlinesticketingsystem.service.RouteService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JourneyServiceImpl implements JourneyService {
    private final FlightService flightService;
    private final RouteService routeService;

    public JourneyServiceImpl(FlightService flightService, RouteService routeService) {
        this.flightService = flightService;
        this.routeService = routeService;
    }


    public List<JourneyResponse> readJourneys(JourneyRequest journeyRequest){
        //TODO JourneyRequest Validation yazılacak, flights -> journeylere maplenecek
        Route onBoundRoute = routeService.readBySourceIataAndDestionationIata(journeyRequest.getSourceAirport(), journeyRequest.getDestinationAirport());
        List<Flight> flightList = flightService.readByRouteAndFlightDate(onBoundRoute, journeyRequest.getFlightDate());


        return new ArrayList<>();
    }
}
