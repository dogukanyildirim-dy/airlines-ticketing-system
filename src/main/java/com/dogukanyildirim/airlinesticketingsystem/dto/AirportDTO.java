package com.dogukanyildirim.airlinesticketingsystem.dto;

import com.dogukanyildirim.airlinesticketingsystem.dto.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirportDTO extends BaseDTO {
    private String airportName;
    private String iataCode;
    private String icaoCode;
    private String city;
    private String country;
    private Double latitude;
    private Double longitude;
    private Integer altitude;
    private Boolean isActive;
}
