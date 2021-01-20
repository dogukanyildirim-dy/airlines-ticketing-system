package com.dogukanyildirim.airlinesticketingsystem.dao;

import com.dogukanyildirim.airlinesticketingsystem.domain.passenger.TicketPriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketPriceHistoryRepository extends JpaRepository<TicketPriceHistory, Integer> {
    Optional<TicketPriceHistory> findFirstByPurchaseCodeOrderByCreatedDateDesc(String purchaseCode);

    boolean existsByPurchaseCodeAndPriceAndMaxQuota(String purchaseCode, Float price, Integer maxQuota);
}
