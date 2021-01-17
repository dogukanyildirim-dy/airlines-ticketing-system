package com.dogukanyildirim.airlinesticketingsystem.dto;

import com.dogukanyildirim.airlinesticketingsystem.dto.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteDTO extends BaseDTO {
    private AirportDTO sourceAirport;
    private AirportDTO destinationAirport;
}
