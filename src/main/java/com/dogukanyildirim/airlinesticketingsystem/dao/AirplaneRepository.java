package com.dogukanyildirim.airlinesticketingsystem.dao;

import com.dogukanyildirim.airlinesticketingsystem.domain.management.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Integer> {
}
