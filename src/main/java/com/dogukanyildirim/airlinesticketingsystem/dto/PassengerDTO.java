package com.dogukanyildirim.airlinesticketingsystem.dto;

import com.dogukanyildirim.airlinesticketingsystem.dto.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassengerDTO extends BaseDTO {
    private String firstName;
    private String lastName;
    private String password;
    private String telNo;
    private String email;
}
