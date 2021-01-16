package com.dogukanyildirim.airlinesticketingsystem.dto;

import com.dogukanyildirim.airlinesticketingsystem.dto.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirplaneDTO extends BaseDTO {
    private String airplaneName;
    private Integer numberOfSeats;
    private String iataCode;
    private String icaoCode;
}
