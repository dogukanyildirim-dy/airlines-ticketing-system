package com.dogukanyildirim.airlinesticketingsystem.service.impl;

import com.dogukanyildirim.airlinesticketingsystem.dao.FlightRepository;
import com.dogukanyildirim.airlinesticketingsystem.domain.management.Flight;
import com.dogukanyildirim.airlinesticketingsystem.domain.management.FlightPackage;
import com.dogukanyildirim.airlinesticketingsystem.domain.management.Route;
import com.dogukanyildirim.airlinesticketingsystem.exception.ServiceException;
import com.dogukanyildirim.airlinesticketingsystem.exception.ValidationException;
import com.dogukanyildirim.airlinesticketingsystem.service.FlightService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.*;

import static com.dogukanyildirim.airlinesticketingsystem.constant.ExceptionMessages.*;

@Service
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public Flight create(Flight flight) {
        if (Objects.isNull(flight)) {
            throw new ServiceException(FLIGHT_OBJECT_MUST_NOT_BE_NULL);
        }
        flightValidation(flight);
        flightPackageValidation(flight.getFlightPackages(), Objects.nonNull(flight.getAirplane()) ? flight.getAirplane().getNumberOfSeats() : null);
        flight.setFlightCode(generateFlightCode(flight.getAirlineCompany().getIataCode(), flight.getFlightDate()));
        generatePurchaseCodes(flight.getFlightPackages());
        return flightRepository.save(flight);
    }

    private void generatePurchaseCodes(Set<FlightPackage> flightPackages) {
        flightPackages.forEach(flightPackage -> {
            flightPackage.setPurchaseCode(UUID.randomUUID().toString());
        });
    }

    private String generateFlightCode(String iataCode, LocalDate flightDate) {
        String flightCode = iataCode + ((int) (Math.random() * 9000) + 1000);
        while (flightRepository.existsByFlightCodeAndFlightDate(flightCode, flightDate)) {
            flightCode = iataCode + ((int) (Math.random() * 9000) + 1000);
        }
        return flightCode;
    }

    @Override
    public Flight read(Integer id) {
        if (Objects.isNull(id)) {
            throw new ServiceException(ID_MUST_NOT_BE_NULL);
        }
        Optional<Flight> resultOpt = flightRepository.findById(id);
        if (!resultOpt.isPresent()) {
            throw new ServiceException(FLIGHT_NOT_FOUND);
        }
        return resultOpt.get();
    }

    @Override
    public List<Flight> readByRouteAndFlightDate(Route route, LocalDate localDate) {
        if (Objects.isNull(route)) {
            throw new ServiceException(ROUTE_IS_NULL);
        }
        if (Objects.isNull(localDate)) {
            throw new ServiceException(FLIGHT_DATE_IS_NULL);
        }
        List<Flight> flightList = flightRepository.findAllByRouteAndFlightDateOrderByDepartureTimeAsc(route, localDate);
        if (flightList.isEmpty()) {
            throw new ServiceException(ANY_FLIGHT_NOT_FOUND, true);
        }
        return flightList;
    }

    @Override
    public List<Flight> readAll() {
        return flightRepository.findAll();
    }

    @Override
    public Flight update(Flight flight) {
        if (Objects.isNull(flight) || Objects.isNull(flight.getId())) {
            throw new ServiceException(FLIGHT_OBJECT_OR_ID_MUST_NOT_BE_NULL);
        }
        flightValidation(flight);
        flightPackageValidation(flight.getFlightPackages(), Objects.nonNull(flight.getAirplane()) ? flight.getAirplane().getNumberOfSeats() : null);
        return flightRepository.save(flight);
    }

    @Override
    public Flight delete(Integer id) {
        if (Objects.isNull(id)) {
            throw new ServiceException(ID_MUST_NOT_BE_NULL);
        }
        Flight flight = read(id);
        flight.setIsDeleted(true);
        flight = flightRepository.save(flight);

        return flight;
    }

    private void flightValidation(Flight flight) throws ValidationException {
        if (Objects.isNull(flight.getRoute())) {
            throw new ValidationException(FLIGHT_ROUTE_MUST_NOT_BE_NULL);
        } else if (Objects.isNull(flight.getRoute().getSourceAirport())) {
            throw new ValidationException(FLIGHT_SOURCE_AP_MUST_NOT_BE_NULL);
        } else if (Objects.isNull(flight.getRoute().getDestinationAirport())) {
            throw new ValidationException(FLIGHT_DEST_AP_MUST_NOT_BE_NULL);
        } else if (Objects.isNull(flight.getAirlineCompany())) {
            throw new ValidationException(FLIGHT_AIRLINE_MUST_NOT_BE_NULL);
        } else if (Objects.isNull(flight.getAirlineCompany().getIataCode())) {
            throw new ValidationException(FLIGHT_AIRLINE_IATA_MUST_NOT_BE_NULL);
        } else if (CollectionUtils.isEmpty(flight.getFlightPackages())) {
            throw new ValidationException(FLIGHT_PACKAGES_MUST_NOT_BE_NULL);
        } else if (Objects.isNull(flight.getFlightDate())) {
            throw new ValidationException(FLIGHT_DATE_MUST_NOT_BE_NULL);
        } else if (Objects.isNull(flight.getDepartureTime())) {
            throw new ValidationException(FLIGHT_DEPARTURE_MUST_NOT_BE_NULL);
        } else if (Objects.isNull(flight.getDuration())) {
            throw new ValidationException(FLIGHT_DURATION_MUST_NOT_BE_NULL);
        }
    }

    private void flightPackageValidation(Set<FlightPackage> flightPackages, Integer airplaneNumberOfSeats) {
        for (FlightPackage flightPackage : flightPackages) {
            if (Objects.isNull(flightPackage.getFlightClass())) {
                throw new ValidationException(FLIGHT_PACKAGE_CLASS_MUST_NOT_BE_NULL);
            } else if (Objects.isNull(flightPackage.getBaseQuota())) {
                throw new ValidationException(FLIGHT_BASE_QUOTA_MUST_NOT_BE_NULL);
            } else if (Objects.isNull(flightPackage.getBasePrice())) {
                throw new ValidationException(FLIGHT_BASE_PRICE_MUST_NOT_BE_NULL);
            }
        }
        int totalQuota = flightPackages.stream().mapToInt(FlightPackage::getBaseQuota).sum();
        if (Objects.nonNull(airplaneNumberOfSeats) && totalQuota > airplaneNumberOfSeats) {
            throw new ValidationException(FLIGHT_TOTAL_QUOTA_EXCEEDED);
        }
    }
}
