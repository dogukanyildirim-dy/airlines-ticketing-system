package com.dogukanyildirim.airlinesticketingsystem.service;


import com.dogukanyildirim.airlinesticketingsystem.domain.passenger.TicketPurchase;
import com.dogukanyildirim.airlinesticketingsystem.dto.request.TicketPurchaseRequest;

import java.util.List;

public interface TicketPurchaseService {

    TicketPurchase create(TicketPurchaseRequest ticketPurchaseRequest);

    TicketPurchase read(Integer id);

    List<TicketPurchase> readAll();

    Integer getCountByFlightIdAndPurchaseCode(Integer flightId, String purchaseCode);

    TicketPurchase delete(Integer id);
}
