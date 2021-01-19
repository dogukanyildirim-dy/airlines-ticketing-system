package com.dogukanyildirim.airlinesticketingsystem.service;

import com.dogukanyildirim.airlinesticketingsystem.dto.request.JourneyRequest;
import com.dogukanyildirim.airlinesticketingsystem.dto.response.MainJourneyResponse;

public interface JourneyService {
    MainJourneyResponse getJourneys(JourneyRequest journeyRequest);
}
