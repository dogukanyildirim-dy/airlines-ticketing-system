package com.dogukanyildirim.airlinesticketingsystem.controller;

import com.dogukanyildirim.airlinesticketingsystem.dto.RestResponse;
import com.dogukanyildirim.airlinesticketingsystem.dto.request.TicketPurchaseRequest;
import com.dogukanyildirim.airlinesticketingsystem.dto.response.TicketSummaryResponse;
import com.dogukanyildirim.airlinesticketingsystem.service.TicketPurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.dogukanyildirim.airlinesticketingsystem.constant.ResponseMessages.*;

/**
 * Bu controller bilet satın alım işlemlerine yönelik entpointlerin bulunduğu controllerdır.
 *
 * @author dogukan.yildirim
 */

@RequestMapping(value = "ticket-purchase")
@RestController
public class TicketPurchaseController {
    private final TicketPurchaseService ticketPurchaseService;

    public TicketPurchaseController(TicketPurchaseService ticketPurchaseService) {
        this.ticketPurchaseService = ticketPurchaseService;
    }

    @PostMapping(value = "buyTicket")
    public ResponseEntity<RestResponse<TicketSummaryResponse>> buyTicket(@RequestBody TicketPurchaseRequest ticketPurchaseRequest) {
        return new ResponseEntity<>(new RestResponse<>(ticketPurchaseService.create(ticketPurchaseRequest), CREATE_TITLE, CREATE_SUCCESS_MESSAGE), HttpStatus.OK);
    }

    @GetMapping(value = "getTicketByPnrCode/{pnrCode}")
    public ResponseEntity<RestResponse<TicketSummaryResponse>> getTicketByPnrCode(@PathVariable("pnrCode") String pnrCode) {
        return new ResponseEntity<>(new RestResponse<>(ticketPurchaseService.read(pnrCode), READ_TITLE, READ_SUCCESS_MESSAGE), HttpStatus.OK);
    }

    @GetMapping(value = "cancelTicketByPnrCode/{pnrCode}")
    public ResponseEntity<RestResponse<TicketSummaryResponse>> cancelTicketByPnrCode(@PathVariable("pnrCode") String pnrCode) {
        return new ResponseEntity<>(new RestResponse<>(ticketPurchaseService.cancelTicket(pnrCode), CANCEL_TICKET_TITLE, CANCEL_TICKET_MESSAGE), HttpStatus.OK);
    }

}
