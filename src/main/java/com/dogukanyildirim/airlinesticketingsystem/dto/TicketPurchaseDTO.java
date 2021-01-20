package com.dogukanyildirim.airlinesticketingsystem.dto;

import com.dogukanyildirim.airlinesticketingsystem.domain.passenger.Passenger;
import com.dogukanyildirim.airlinesticketingsystem.dto.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class TicketPurchaseDTO extends BaseDTO {
    private Set<PassengerDTO> passengers;
    private FlightDTO flight;
    private String purchaseCode;
    private String pnrCode;
    private Boolean isCancelled;
    private PurchaseDetailDTO purchaseDetail;
}
