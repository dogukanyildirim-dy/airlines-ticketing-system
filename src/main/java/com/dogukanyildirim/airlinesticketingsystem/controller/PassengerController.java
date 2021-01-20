package com.dogukanyildirim.airlinesticketingsystem.controller;

import com.dogukanyildirim.airlinesticketingsystem.domain.passenger.Passenger;
import com.dogukanyildirim.airlinesticketingsystem.dto.PassengerDTO;
import com.dogukanyildirim.airlinesticketingsystem.dto.RestResponse;
import com.dogukanyildirim.airlinesticketingsystem.dto.PassengerDTO;
import com.dogukanyildirim.airlinesticketingsystem.dto.mapper.ObjectMapper;
import com.dogukanyildirim.airlinesticketingsystem.service.PassengerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dogukanyildirim.airlinesticketingsystem.constant.ResponseMessages.*;

/**
 * Bu controller yolcu CRUD işlemlerine yönelik entpointlerin bulunduğu controllerdır.
 *
 * @author dogukan.yildirim
 */

@RequestMapping(value = "passenger")
@RestController
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @PostMapping(value = "create")
    public ResponseEntity<RestResponse<PassengerDTO>> create(@RequestBody PassengerDTO passengerDTO) {
        Passenger result = passengerService.create(ObjectMapper.getInstance().map(passengerDTO, Passenger.class));
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().map(result, PassengerDTO.class), CREATE_TITLE, CREATE_SUCCESS_MESSAGE), HttpStatus.OK);
    }

    @GetMapping(value = "read/{id}")
    public ResponseEntity<RestResponse<PassengerDTO>> read(@PathVariable("id") Integer id) {
        Passenger result = passengerService.read(id);
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().map(result, PassengerDTO.class), READ_TITLE, READ_SUCCESS_MESSAGE), HttpStatus.OK);
    }

    @GetMapping(value = "readAll")
    public ResponseEntity<RestResponse<List<PassengerDTO>>> readAll() {
        List<Passenger> result = passengerService.readAll();
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().mapAll(result, PassengerDTO.class), READ_LIST_TITLE, READ_LIST_SUCCESS_MESSAGE), HttpStatus.OK);
    }

    @PutMapping(value = "update")
    public ResponseEntity<RestResponse<PassengerDTO>> update(@RequestBody PassengerDTO passengerDTO) {
        Passenger result = passengerService.update(ObjectMapper.getInstance().map(passengerDTO, Passenger.class));
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().map(result, PassengerDTO.class), UPDATE_TITLE, UPDATE_SUCCESS_MESSAGE), HttpStatus.OK);
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<RestResponse<PassengerDTO>> delete(@PathVariable("id") Integer id) {
        Passenger result = passengerService.delete(id);
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().map(result, PassengerDTO.class), DELETE_TITLE, DELETE_SUCCESS_MESSAGE), HttpStatus.OK);
    }
}
