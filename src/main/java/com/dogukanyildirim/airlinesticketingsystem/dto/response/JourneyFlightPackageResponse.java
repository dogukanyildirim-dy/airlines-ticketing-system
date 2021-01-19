package com.dogukanyildirim.airlinesticketingsystem.dto.response;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class JourneyFlightPackageResponse {
    private String flightClass;
    private Integer currentQuota;
    private Float currentPrice;
    private Float baggage;
    private Float cabinBaggage;
    private String purchaseCode;
    private Integer lastQuotaAtCurrentPrice;
    private Float tax;
    private Float fuelCharge;
    private Float netPrice;
}
