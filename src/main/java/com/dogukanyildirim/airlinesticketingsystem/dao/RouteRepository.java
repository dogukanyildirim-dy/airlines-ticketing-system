package com.dogukanyildirim.airlinesticketingsystem.dao;

import com.dogukanyildirim.airlinesticketingsystem.domain.management.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {
    Optional<Route> findBySourceAirport_IataCodeAndDestinationAirport_IataCode(String sourceAirport_iataCode, String destinationAirport_iataCode);

    List<Route> findBySourceAirport_IataCode(String sourceAirport_iataCode);
}
