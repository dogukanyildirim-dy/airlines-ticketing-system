package com.dogukanyildirim.airlinesticketingsystem.dto.request;

import com.dogukanyildirim.airlinesticketingsystem.dto.PassengerDTO;
import com.dogukanyildirim.airlinesticketingsystem.dto.PurchaseDetailDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class TicketPurchaseRequest {
    private Integer flightId;
    private String purchaseCode;
    private Integer currentQuota;
    private Float currentPrice;
    private Set<PassengerDTO> passengers;
    private PurchaseDetailDTO purchaseDetail;
}
