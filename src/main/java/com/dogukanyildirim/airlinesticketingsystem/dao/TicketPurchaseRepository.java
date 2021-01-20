package com.dogukanyildirim.airlinesticketingsystem.dao;

import com.dogukanyildirim.airlinesticketingsystem.domain.passenger.TicketPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketPurchaseRepository extends JpaRepository<TicketPurchase, Integer> {
    Integer countByFlight_IdAndPurchaseCodeAndIsCancelledIsFalse(Integer flightId, String purchaseCode);

    Optional<TicketPurchase> findByPnrCodeAndIsCancelledIsFalse(String pnrCode);
}
