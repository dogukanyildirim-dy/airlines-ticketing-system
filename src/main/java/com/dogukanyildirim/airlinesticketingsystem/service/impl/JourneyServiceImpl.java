package com.dogukanyildirim.airlinesticketingsystem.service.impl;

import com.dogukanyildirim.airlinesticketingsystem.dao.TicketPriceHistoryRepository;
import com.dogukanyildirim.airlinesticketingsystem.domain.management.Flight;
import com.dogukanyildirim.airlinesticketingsystem.domain.management.FlightPackage;
import com.dogukanyildirim.airlinesticketingsystem.domain.management.Route;
import com.dogukanyildirim.airlinesticketingsystem.domain.passenger.TicketPriceHistory;
import com.dogukanyildirim.airlinesticketingsystem.dto.request.JourneyRequest;
import com.dogukanyildirim.airlinesticketingsystem.dto.response.JourneyFlightPackageResponse;
import com.dogukanyildirim.airlinesticketingsystem.dto.response.JourneyResponse;
import com.dogukanyildirim.airlinesticketingsystem.dto.response.MainJourneyResponse;
import com.dogukanyildirim.airlinesticketingsystem.service.FlightService;
import com.dogukanyildirim.airlinesticketingsystem.service.JourneyService;
import com.dogukanyildirim.airlinesticketingsystem.service.RouteService;
import com.dogukanyildirim.airlinesticketingsystem.service.TicketPurchaseService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.dogukanyildirim.airlinesticketingsystem.constant.Constants.*;

@Service
public class JourneyServiceImpl implements JourneyService {
    private final FlightService flightService;
    private final TicketPurchaseService ticketPurchaseService;
    private final RouteService routeService;
    private final TicketPriceHistoryRepository ticketPriceHistoryRepository;

    public JourneyServiceImpl(FlightService flightService, TicketPurchaseService ticketPurchaseService, RouteService routeService, TicketPriceHistoryRepository ticketPriceHistoryRepository) {
        this.flightService = flightService;
        this.ticketPurchaseService = ticketPurchaseService;
        this.routeService = routeService;
        this.ticketPriceHistoryRepository = ticketPriceHistoryRepository;
    }

    @Override
    public MainJourneyResponse getJourneys(JourneyRequest journeyRequest) {
        MainJourneyResponse mainJourneyResponse = new MainJourneyResponse();
        Route outboundRoute = routeService.readBySourceIataAndDestionationIata(journeyRequest.getSourceAirport(), journeyRequest.getDestinationAirport());
        List<JourneyResponse> outboundJourneyResponses = new ArrayList<>();
        createJourneysByRoute(journeyRequest, journeyRequest.getFlightDate(), outboundJourneyResponses, outboundRoute);
        mainJourneyResponse.setOutboundJourneys(outboundJourneyResponses.stream()
                .sorted(Comparator.comparing(JourneyResponse::getDepartureTime, Comparator.naturalOrder()))
                .collect(Collectors.toList()));
        if (!journeyRequest.getIsOneWay()) {
            List<JourneyResponse> returnJourneyResponses = new ArrayList<>();
            Route returnRoute = routeService.readBySourceIataAndDestionationIata(journeyRequest.getDestinationAirport(), journeyRequest.getSourceAirport());
            createJourneysByRoute(journeyRequest, journeyRequest.getReturnFlightDate(), returnJourneyResponses, returnRoute);
            mainJourneyResponse.setReturnJourneys(returnJourneyResponses.stream()
                    .sorted(Comparator.comparing(JourneyResponse::getDepartureTime, Comparator.naturalOrder()))
                    .collect(Collectors.toList()));
        }
        return mainJourneyResponse;
    }

    private void createJourneysByRoute(JourneyRequest journeyRequest, LocalDate flightDate, List<JourneyResponse> journeyResponses, Route route) {
        List<Flight> flightList = flightService.readByRouteAndFlightDate(route, flightDate);
        for (Flight flight : flightList) {
            JourneyResponse journeyResponse = new JourneyResponse();
            journeyResponse.setFlightId(flight.getId());
            journeyResponse.setFlightDate(flight.getFlightDate());
            journeyResponse.setFlightCode(flight.getFlightCode());
            journeyResponse.setDepartureTime(flight.getDepartureTime());
            journeyResponse.setDuration(flight.getDuration());
            journeyResponse.setArrivalTime(flight.getDepartureTime().plusHours(flight.getDuration().getHour()).plusMinutes(flight.getDuration().getMinute()));
            journeyResponse.setRouteId(route.getId());
            journeyResponse.setRouteSource(route.getSourceAirport().getAirportName() + ", " + route.getSourceAirport().getIataCode() + ", " + route.getSourceAirport().getCity());
            journeyResponse.setRouteDestination(route.getDestinationAirport().getAirportName() + ", " + route.getDestinationAirport().getIataCode() + ", " + route.getDestinationAirport().getCity());
            journeyResponse.setFlightType(flight.getIsDirectly() ? DIRECTLY : INDIRECTLY);
            journeyResponse.setAirlineCompanyId(flight.getAirlineCompany().getId());
            journeyResponse.setAirlineCompany(flight.getAirlineCompany().getAirlineName());

            List<JourneyFlightPackageResponse> journeyFlightPackageResponseList = new ArrayList<>();
            if (Objects.nonNull(flight.getFlightPackages())) {
                for (FlightPackage flightPackage : flight.getFlightPackages()) {
                    JourneyFlightPackageResponse journeyFlightPackageResponse = new JourneyFlightPackageResponse();
                    journeyFlightPackageResponse.setFlightClass(flightPackage.getFlightClass().getClassName());
                    journeyFlightPackageResponse.setBaggage(flightPackage.getBaggage());
                    journeyFlightPackageResponse.setCabinBaggage(flightPackage.getCabinBaggage());
                    calculateTicketPriceAndQuota(flightPackage, journeyFlightPackageResponse);
                    journeyFlightPackageResponse.setFuelCharge(flight.getAirlineCompany().getFuelCharge());
                    journeyFlightPackageResponse.setTax(journeyFlightPackageResponse.getCurrentPrice() * TAX_PERCENT);
                    journeyFlightPackageResponse.setNetPrice(journeyFlightPackageResponse.getCurrentPrice() + journeyFlightPackageResponse.getFuelCharge() + journeyFlightPackageResponse.getTax());
                    journeyFlightPackageResponse.setPriceForPassengerCount(journeyFlightPackageResponse.getNetPrice() * journeyRequest.getNumberOfPassengers());
                    journeyFlightPackageResponseList.add(journeyFlightPackageResponse);
                }
            }
            journeyResponse.setJourneyFlightPackages(journeyFlightPackageResponseList);
            journeyResponses.add(journeyResponse);
        }
    }

    private void calculateTicketPriceAndQuota(FlightPackage flightPackage, JourneyFlightPackageResponse journeyFlightPackageResponse) {
        Optional<TicketPriceHistory> ticketPriceHistory = ticketPriceHistoryRepository.findFirstByPurchaseCodeOrderByCreatedDateDesc(flightPackage.getPurchaseCode());
        Integer purchasedTicketCount = ticketPurchaseService.getCountByFlightIdAndPurchaseCode(flightPackage.getFlight().getId(), flightPackage.getPurchaseCode());

        if (ticketPriceHistory.isPresent() && flightPackage.getBaseQuota() > purchasedTicketCount) {
            float lastPrice = ticketPriceHistory.get().getPrice();
            int lastMaxQuota = ticketPriceHistory.get().getMaxQuota();
            int remainingQuota = lastMaxQuota - purchasedTicketCount;

            if (remainingQuota > 0) {
                journeyFlightPackageResponse.setCurrentPrice(lastPrice);
                journeyFlightPackageResponse.setCurrentQuota(lastMaxQuota);
                journeyFlightPackageResponse.setLastQuotaAtCurrentPrice(remainingQuota);
            } else {
                journeyFlightPackageResponse.setCurrentPrice(lastPrice + (lastPrice * OVER_QUOTA_RAISE));
                journeyFlightPackageResponse.setCurrentQuota(Math.round(lastMaxQuota + (lastMaxQuota * OVER_QUOTA_RAISE)));
                journeyFlightPackageResponse.setLastQuotaAtCurrentPrice(Math.round(lastMaxQuota + (lastMaxQuota * OVER_QUOTA_RAISE)) - lastMaxQuota);
            }
        } else {
            journeyFlightPackageResponse.setCurrentPrice(flightPackage.getBasePrice());
            journeyFlightPackageResponse.setCurrentQuota(flightPackage.getBaseQuota());
            journeyFlightPackageResponse.setLastQuotaAtCurrentPrice(flightPackage.getBaseQuota() - purchasedTicketCount);
        }
    }
}
