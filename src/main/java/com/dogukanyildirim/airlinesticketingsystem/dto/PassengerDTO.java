package com.dogukanyildirim.airlinesticketingsystem.dto;

import com.dogukanyildirim.airlinesticketingsystem.dto.base.BaseDTO;
import com.dogukanyildirim.airlinesticketingsystem.enums.GenderEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PassengerDTO extends BaseDTO {
    private String firstName;
    private String lastName;
    private String identificationNumber;
    private LocalDate birthday;
    private GenderEnum gender;
}
