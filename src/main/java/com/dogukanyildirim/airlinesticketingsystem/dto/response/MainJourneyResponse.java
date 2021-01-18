package com.dogukanyildirim.airlinesticketingsystem.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class MainJourneyResponse {
    List<JourneyResponse> outboundJourneys;
    List<JourneyResponse> returnJourneys;
}
