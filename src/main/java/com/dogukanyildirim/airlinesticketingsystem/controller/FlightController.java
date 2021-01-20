package com.dogukanyildirim.airlinesticketingsystem.controller;

import com.dogukanyildirim.airlinesticketingsystem.domain.management.Flight;
import com.dogukanyildirim.airlinesticketingsystem.dto.FlightDTO;
import com.dogukanyildirim.airlinesticketingsystem.dto.RestResponse;
import com.dogukanyildirim.airlinesticketingsystem.dto.mapper.ObjectMapper;
import com.dogukanyildirim.airlinesticketingsystem.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dogukanyildirim.airlinesticketingsystem.constant.ResponseMessages.*;

/**
 * Bu controller uçuş CRUD işlemlerine yönelik entpointlerin bulunduğu controllerdır.
 *
 * @author dogukan.yildirim
 */

@RequestMapping(value = "flight")
@RestController
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping(value = "create")
    public ResponseEntity<RestResponse<FlightDTO>> create(@RequestBody FlightDTO flightDTO) {
        Flight result = flightService.create(ObjectMapper.getInstance().map(flightDTO, Flight.class));
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().map(result, FlightDTO.class), CREATE_TITLE, CREATE_SUCCESS_MESSAGE), HttpStatus.OK);
    }

    @GetMapping(value = "read/{id}")
    public ResponseEntity<RestResponse<FlightDTO>> read(@PathVariable("id") Integer id) {
        Flight result = flightService.read(id);
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().map(result, FlightDTO.class), READ_TITLE, READ_SUCCESS_MESSAGE), HttpStatus.OK);
    }

    @GetMapping(value = "readAll")
    public ResponseEntity<RestResponse<List<FlightDTO>>> readAll() {
        List<Flight> result = flightService.readAll();
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().mapAll(result, FlightDTO.class), READ_LIST_TITLE, READ_LIST_SUCCESS_MESSAGE), HttpStatus.OK);
    }

    @PutMapping(value = "update")
    public ResponseEntity<RestResponse<FlightDTO>> update(@RequestBody FlightDTO flightDTO) {
        Flight result = flightService.update(ObjectMapper.getInstance().map(flightDTO, Flight.class));
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().map(result, FlightDTO.class), UPDATE_TITLE, UPDATE_SUCCESS_MESSAGE), HttpStatus.OK);
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<RestResponse<FlightDTO>> delete(@PathVariable("id") Integer id) {
        Flight result = flightService.delete(id);
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().map(result, FlightDTO.class), DELETE_TITLE, DELETE_SUCCESS_MESSAGE), HttpStatus.OK);
    }
}
