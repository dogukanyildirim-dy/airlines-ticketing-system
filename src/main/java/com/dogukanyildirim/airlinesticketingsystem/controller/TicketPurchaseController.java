package com.dogukanyildirim.airlinesticketingsystem.controller;

import com.dogukanyildirim.airlinesticketingsystem.dto.RestResponse;
import com.dogukanyildirim.airlinesticketingsystem.dto.RouteDTO;
import com.dogukanyildirim.airlinesticketingsystem.dto.mapper.ObjectMapper;
import com.dogukanyildirim.airlinesticketingsystem.dto.request.TicketPurchaseRequest;
import com.dogukanyildirim.airlinesticketingsystem.service.TicketPurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.dogukanyildirim.airlinesticketingsystem.constant.ResponseMessages.CREATE_SUCCESS_MESSAGE;
import static com.dogukanyildirim.airlinesticketingsystem.constant.ResponseMessages.CREATE_TITLE;

@RequestMapping(value = "ticket-purchase")
@RestController
public class TicketPurchaseController {
    private final TicketPurchaseService ticketPurchaseService;

    public TicketPurchaseController(TicketPurchaseService ticketPurchaseService) {
        this.ticketPurchaseService = ticketPurchaseService;
    }

    @PostMapping(value = "buyTicket")
    public ResponseEntity<RestResponse<RouteDTO>> create(@RequestBody TicketPurchaseRequest ticketPurchaseRequest) {

         return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().map(null, RouteDTO.class), CREATE_TITLE, CREATE_SUCCESS_MESSAGE), HttpStatus.OK);
    }

}
