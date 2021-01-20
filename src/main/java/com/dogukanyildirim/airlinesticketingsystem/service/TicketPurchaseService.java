package com.dogukanyildirim.airlinesticketingsystem.service;


import com.dogukanyildirim.airlinesticketingsystem.dto.request.TicketPurchaseRequest;
import com.dogukanyildirim.airlinesticketingsystem.dto.response.TicketSummaryResponse;

public interface TicketPurchaseService {

    TicketSummaryResponse create(TicketPurchaseRequest ticketPurchaseRequest);

    TicketSummaryResponse read(String pnrCode);

    Integer getCountByFlightIdAndPurchaseCode(Integer flightId, String purchaseCode);

    TicketSummaryResponse cancelTicket(String pnrCode);
}
