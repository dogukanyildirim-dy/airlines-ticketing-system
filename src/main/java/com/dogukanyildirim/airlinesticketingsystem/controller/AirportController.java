package com.dogukanyildirim.airlinesticketingsystem.controller;

import com.dogukanyildirim.airlinesticketingsystem.domain.Airport;
import com.dogukanyildirim.airlinesticketingsystem.dto.AirportDTO;
import com.dogukanyildirim.airlinesticketingsystem.dto.RestResponse;
import com.dogukanyildirim.airlinesticketingsystem.dto.mapper.ObjectMapper;
import com.dogukanyildirim.airlinesticketingsystem.service.AirportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dogukanyildirim.airlinesticketingsystem.constant.ResponseMessages.*;

@RequestMapping(value = "airport")
@RestController
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @PostMapping(value = "create")
    public ResponseEntity<RestResponse<AirportDTO>> create(@RequestBody AirportDTO airportDTO) {
        Airport result = airportService.create(ObjectMapper.getInstance().map(airportDTO, Airport.class));
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().map(result, AirportDTO.class), CREATE_TITLE, CREATE_SUCCESS_MESSAGE), HttpStatus.OK);
    }

    @GetMapping(value = "read/{id}")
    public ResponseEntity<RestResponse<AirportDTO>> read(@PathVariable("id") Integer id) {
        Airport result = airportService.read(id);
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().map(result, AirportDTO.class), READ_TITLE, READ_SUCCESS_MESSAGE), HttpStatus.OK);
    }

    @GetMapping(value = "readAll")
    public ResponseEntity<RestResponse<List<AirportDTO>>> readAll() {
        List<Airport> result = airportService.readAll();
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().mapAll(result, AirportDTO.class), READ_LIST_TITLE, READ_LIST_SUCCESS_MESSAGE), HttpStatus.OK);
    }

    @PutMapping(value = "update")
    public ResponseEntity<RestResponse<AirportDTO>> update(@RequestBody AirportDTO airportDTO) {
        Airport result = airportService.create(ObjectMapper.getInstance().map(airportDTO, Airport.class));
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().map(result, AirportDTO.class), UPDATE_TITLE, UPDATE_SUCCESS_MESSAGE), HttpStatus.OK);
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<RestResponse<AirportDTO>> delete(@PathVariable("id") Integer id) {
        Airport result = airportService.delete(id);
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().map(result, AirportDTO.class), DELETE_TITLE, DELETE_SUCCESS_MESSAGE), HttpStatus.OK);
    }
}
