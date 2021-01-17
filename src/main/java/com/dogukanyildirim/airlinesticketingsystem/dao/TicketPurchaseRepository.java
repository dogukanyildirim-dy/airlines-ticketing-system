package com.dogukanyildirim.airlinesticketingsystem.dao;

import com.dogukanyildirim.airlinesticketingsystem.domain.passenger.TicketPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketPurchaseRepository extends JpaRepository<TicketPurchase, Integer> {

}
