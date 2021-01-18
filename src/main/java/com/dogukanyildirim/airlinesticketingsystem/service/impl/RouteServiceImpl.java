package com.dogukanyildirim.airlinesticketingsystem.service.impl;

import com.dogukanyildirim.airlinesticketingsystem.dao.RouteRepository;
import com.dogukanyildirim.airlinesticketingsystem.domain.management.Route;
import com.dogukanyildirim.airlinesticketingsystem.exception.ServiceException;
import com.dogukanyildirim.airlinesticketingsystem.service.RouteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.dogukanyildirim.airlinesticketingsystem.constant.ExceptionMessages.*;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;

    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public Route create(Route route) {
        if (Objects.isNull(route)) {
            throw new ServiceException(ROUTE_OBJECT_MUST_NOT_BE_NULL);
        }
        return routeRepository.save(route);
    }

    @Override
    public Route read(Integer id) {
        if (Objects.isNull(id)) {
            throw new ServiceException(ID_MUST_NOT_BE_NULL);
        }
        Optional<Route> resultOpt = routeRepository.findById(id);
        if (!resultOpt.isPresent()) {
            throw new ServiceException(ROUTE_NOT_FOUND);
        }
        return resultOpt.get();
    }

    @Override
    public Route readBySourceIataAndDestionationIata(String sIata, String dIata) {
        Optional<Route> resultOpt = routeRepository.findBySourceAirport_IataCodeAndDestinationAirport_IataCode(sIata, dIata);
        if (!resultOpt.isPresent()) {
            throw new ServiceException(ROUTE_NOT_FOUND);
        }
        return resultOpt.get();
    }

    @Override
    public List<Route> readAll() {
        return routeRepository.findAll();
    }

    @Override
    public Route update(Route route) {
        if (Objects.isNull(route) || Objects.isNull(route.getId())) {
            throw new ServiceException(ROUTE_OBJECT_OR_ID_MUST_NOT_BE_NULL);
        }
        return routeRepository.save(route);
    }

    @Override
    public Route delete(Integer id) {
        if (Objects.isNull(id)) {
            throw new ServiceException(ID_MUST_NOT_BE_NULL);
        }
        Route route = read(id);
        route.setIsDeleted(true);
        route = routeRepository.save(route);

        return route;
    }
}
