package com.dogukanyildirim.airlinesticketingsystem.controller;

import com.dogukanyildirim.airlinesticketingsystem.domain.management.Airplane;
import com.dogukanyildirim.airlinesticketingsystem.dto.AirplaneDTO;
import com.dogukanyildirim.airlinesticketingsystem.dto.RestResponse;
import com.dogukanyildirim.airlinesticketingsystem.dto.mapper.ObjectMapper;
import com.dogukanyildirim.airlinesticketingsystem.service.AirplaneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dogukanyildirim.airlinesticketingsystem.constant.ResponseMessages.*;

@RequestMapping(value = "airplane")
@RestController
public class AirplaneController {

    private final AirplaneService airplaneService;

    public AirplaneController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @PostMapping(value = "create")
    public ResponseEntity<RestResponse<AirplaneDTO>> create(@RequestBody AirplaneDTO airplaneDTO) {
        Airplane result = airplaneService.create(ObjectMapper.getInstance().map(airplaneDTO, Airplane.class));
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().map(result, AirplaneDTO.class), CREATE_TITLE, CREATE_SUCCESS_MESSAGE), HttpStatus.OK);
    }

    @GetMapping(value = "read/{id}")
    public ResponseEntity<RestResponse<AirplaneDTO>> read(@PathVariable("id") Integer id) {
        Airplane result = airplaneService.read(id);
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().map(result, AirplaneDTO.class), READ_TITLE, READ_SUCCESS_MESSAGE), HttpStatus.OK);
    }

    @GetMapping(value = "readAll")
    public ResponseEntity<RestResponse<List<AirplaneDTO>>> readAll() {
        List<Airplane> result = airplaneService.readAll();
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().mapAll(result, AirplaneDTO.class), READ_LIST_TITLE, READ_LIST_SUCCESS_MESSAGE), HttpStatus.OK);
    }

    @PutMapping(value = "update")
    public ResponseEntity<RestResponse<AirplaneDTO>> update(@RequestBody AirplaneDTO airplaneDTO) {
        Airplane result = airplaneService.update(ObjectMapper.getInstance().map(airplaneDTO, Airplane.class));
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().map(result, AirplaneDTO.class), UPDATE_TITLE, UPDATE_SUCCESS_MESSAGE), HttpStatus.OK);
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<RestResponse<AirplaneDTO>> delete(@PathVariable("id") Integer id) {
        Airplane result = airplaneService.delete(id);
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().map(result, AirplaneDTO.class), DELETE_TITLE, DELETE_SUCCESS_MESSAGE), HttpStatus.OK);
    }
}
