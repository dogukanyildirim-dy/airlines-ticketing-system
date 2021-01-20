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

    /**
     * Rota kaydetmek için yazılmış servis metotudur.
     *
     * @param route Rota Entity
     * @return Kaydedilen Rota Entity
     */
    @Override
    public Route create(Route route) {
        if (Objects.isNull(route)) {
            throw new ServiceException(ROUTE_OBJECT_MUST_NOT_BE_NULL);
        }
        return routeRepository.save(route);
    }

    /**
     * ID ile bir Rota sorgulamak için yazılmış servis metotudur.
     *
     * @param id Rota ID
     * @return Rota Entity
     */
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

    /**
     * İki havaalanı IATA kodu ile Rota sorgulayan metottur.
     *
     * @param sIata Başlangıç Havaalanı IATA kodu
     * @param dIata Hedef Havaalanı IATA kodu
     * @return Rota Entity
     */
    @Override
    public Route readBySourceIataAndDestionationIata(String sIata, String dIata) {
        Optional<Route> resultOpt = routeRepository.findBySourceAirport_IataCodeAndDestinationAirport_IataCode(sIata, dIata);
        if (!resultOpt.isPresent()) {
            throw new ServiceException(ROUTE_NOT_FOUND);
        }
        return resultOpt.get();
    }

    /**
     * Havaalanı IATA kodu ile o havaalanına tanımlı rotaları sorgulayan metoddur.
     *
     * @param sIata Havaalanı IATA kodu
     * @return Rota listesi
     */
    @Override
    public List<Route> readBySourceIata(String sIata) {
        return routeRepository.findBySourceAirport_IataCode(sIata);
    }

    /**
     * Tüm rotaları sorgulayan metottur.
     *
     * @return Rota listesi
     */
    @Override
    public List<Route> readAll() {
        return routeRepository.findAll();
    }

    /**
     * Rota güncelleme işlemini yapan metottur.
     *
     * @param route Rota Entity
     * @return Rota Entity
     */
    @Override
    public Route update(Route route) {
        if (Objects.isNull(route) || Objects.isNull(route.getId())) {
            throw new ServiceException(ROUTE_OBJECT_OR_ID_MUST_NOT_BE_NULL);
        }
        return routeRepository.save(route);
    }

    /**
     * Rota silmek işlemini yapan metottur.
     *
     * @param id Rota id
     * @return Rota Entity
     */
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
