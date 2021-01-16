package com.dogukanyildirim.airlinesticketingsystem.service.impl;

import com.dogukanyildirim.airlinesticketingsystem.dao.AirportRepository;
import com.dogukanyildirim.airlinesticketingsystem.domain.Airport;
import com.dogukanyildirim.airlinesticketingsystem.exception.ServiceException;
import com.dogukanyildirim.airlinesticketingsystem.service.AirportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.dogukanyildirim.airlinesticketingsystem.constant.ExceptionMessages.*;

@Service
public class AirportServiceImpl implements AirportService {
    private final AirportRepository airportRepository;

    public AirportServiceImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public Airport create(Airport airport) {
        if (Objects.isNull(airport)) {
            throw new ServiceException(AIRPORT_OBJECT_MUST_NOT_BE_NULL);
        }
        return airportRepository.save(airport);
    }

    @Override
    public Airport read(Integer id) {
        if (Objects.isNull(id)) {
            throw new ServiceException(ID_MUST_NOT_BE_NULL);
        }
        Optional<Airport> resultOpt = airportRepository.findById(id);
        if (!resultOpt.isPresent()) {
            throw new ServiceException(AIRPORT_NOT_FOUND);
        }
        return resultOpt.get();
    }

    @Override
    public List<Airport> readAll() {
        return airportRepository.findAll();
    }

    @Override
    public Airport update(Airport airport) {
        if (Objects.isNull(airport) || Objects.isNull(airport.getId())) {
            throw new ServiceException(AIRPORT_OBJECT_OR_ID_MUST_NOT_BE_NULL);
        }
        return airportRepository.save(airport);
    }

    @Override
    public Airport delete(Integer id) {
        if (Objects.isNull(id)) {
            throw new ServiceException(ID_MUST_NOT_BE_NULL);
        }
        Airport airport = read(id);
        airport.setIsDeleted(true);
        airport = airportRepository.save(airport);

        return airport;
    }
}
