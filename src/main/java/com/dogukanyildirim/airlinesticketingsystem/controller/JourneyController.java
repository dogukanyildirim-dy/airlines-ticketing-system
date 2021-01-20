package com.dogukanyildirim.airlinesticketingsystem.controller;

import com.dogukanyildirim.airlinesticketingsystem.dto.RestResponse;
import com.dogukanyildirim.airlinesticketingsystem.dto.request.JourneyRequest;
import com.dogukanyildirim.airlinesticketingsystem.dto.response.MainJourneyResponse;
import com.dogukanyildirim.airlinesticketingsystem.service.JourneyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.dogukanyildirim.airlinesticketingsystem.constant.ResponseMessages.READ_SUCCESS_MESSAGE;
import static com.dogukanyildirim.airlinesticketingsystem.constant.ResponseMessages.READ_TITLE;

/**
 * Bu controller belirtilen özelliklerdeki uçak seferleri getirmeye yönelik entpointin bulunduğu controllerdır.
 *
 * @author dogukan.yildirim
 */

@RequestMapping(value = "journey")
@RestController
public class JourneyController {

    private final JourneyService journeyService;

    public JourneyController(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    @PostMapping(value = "getJourneys")
    public ResponseEntity<RestResponse<MainJourneyResponse>> create(@RequestBody JourneyRequest journeyRequest) {
        MainJourneyResponse mainJourneyResponse = journeyService.getJourneys(journeyRequest);
        return new ResponseEntity<>(new RestResponse<>(mainJourneyResponse, READ_TITLE, READ_SUCCESS_MESSAGE), HttpStatus.OK);
    }

}
