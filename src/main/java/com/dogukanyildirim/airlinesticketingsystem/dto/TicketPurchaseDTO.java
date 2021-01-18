package com.dogukanyildirim.airlinesticketingsystem.dto;

import com.dogukanyildirim.airlinesticketingsystem.dto.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketPurchaseDTO extends BaseDTO {
    private PassengerDTO passenger;
    private FlightDTO flight;
    private String purchaseCode;
    private String pnrCode;
    private Boolean isCancelled;
    private PurchaseDetailDTO purchaseDetail;
}
