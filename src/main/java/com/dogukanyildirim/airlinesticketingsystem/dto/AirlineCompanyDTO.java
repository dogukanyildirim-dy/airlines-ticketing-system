package com.dogukanyildirim.airlinesticketingsystem.dto;

import com.dogukanyildirim.airlinesticketingsystem.dto.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirlineCompanyDTO extends BaseDTO {
    private String airlineName;
    private String airlineAlias;
    private String airlineCallsign;
    private String iataCode; // ucus no hesanınca yanına koyulacak
    private String icaoCode;
    private String country;
}
