package com.dogukanyildirim.airlinesticketingsystem.service;


import com.dogukanyildirim.airlinesticketingsystem.domain.passenger.TicketPurchase;

import java.util.List;

public interface TicketPurchaseService {
    TicketPurchase create(TicketPurchase route);

    TicketPurchase read(Integer id);

    List<TicketPurchase> readAll();

    TicketPurchase update(TicketPurchase route);

    TicketPurchase delete(Integer id);
}