package com.dogukanyildirim.airlinesticketingsystem.dto;

import com.dogukanyildirim.airlinesticketingsystem.dto.base.BaseDTO;
import com.dogukanyildirim.airlinesticketingsystem.enums.ClassEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightPackageDTO extends BaseDTO {
    @JsonIgnore
    private FlightDTO flight;
    private ClassEnum flightClass;
    private Integer baseQuota;
    private Float basePrice;
    private Float baggage;
    private Float cabinBaggage;
    private String purchaseCode;
}
