package com.dogukanyildirim.airlinesticketingsystem.dao;

import com.dogukanyildirim.airlinesticketingsystem.domain.management.AirlineCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineCompanyRepository extends JpaRepository<AirlineCompany, Integer> {
}
