package com.dogukanyildirim.airlinesticketingsystem.dto.response;

import com.dogukanyildirim.airlinesticketingsystem.enums.ClassEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JourneyFlightPackageResponse {
    private ClassEnum flightClass;
    private Integer currentQuota;
    private Float currentPrice;
    private Float baggage;
    private Float cabinBaggage;
    private String purchaseCode;
    private Integer lastQuotaAtCurrentPrice;
    private Float tax;
    private Float fuelCharge;
    private Float netPrice;
    private Float priceForPassengerCount;
}
