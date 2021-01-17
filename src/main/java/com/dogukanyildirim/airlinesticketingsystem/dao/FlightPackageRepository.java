package com.dogukanyildirim.airlinesticketingsystem.dao;

import com.dogukanyildirim.airlinesticketingsystem.domain.management.FlightPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightPackageRepository extends JpaRepository<FlightPackage, Integer> {
}
