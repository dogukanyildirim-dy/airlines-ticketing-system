package com.dogukanyildirim.airlinesticketingsystem.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassengerResponse {
    private String firstName;
    private String lastName;

    public PassengerResponse(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
