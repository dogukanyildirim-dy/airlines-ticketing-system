package com.dogukanyildirim.airlinesticketingsystem.controller;

import com.dogukanyildirim.airlinesticketingsystem.domain.management.AirlineCompany;
import com.dogukanyildirim.airlinesticketingsystem.dto.AirlineCompanyDTO;
import com.dogukanyildirim.airlinesticketingsystem.dto.RestResponse;
import com.dogukanyildirim.airlinesticketingsystem.dto.mapper.ObjectMapper;
import com.dogukanyildirim.airlinesticketingsystem.service.AirlineCompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dogukanyildirim.airlinesticketingsystem.constant.ResponseMessages.*;

/**
 * Bu controller havayolu şirketi CRUD işlemlerine yönelik entpointlerin bulunduğu controllerdır.
 *
 * @author dogukan.yildirim
 */


@RequestMapping(value = "airline-company")
@RestController
public class AirlineCompanyController {

    private final AirlineCompanyService airlineCompanyService;

    public AirlineCompanyController(AirlineCompanyService airlineCompanyService) {
        this.airlineCompanyService = airlineCompanyService;
    }

    @PostMapping(value = "create")
    public ResponseEntity<RestResponse<AirlineCompanyDTO>> create(@RequestBody AirlineCompanyDTO airlineCompanyDTO) {
        AirlineCompany result = airlineCompanyService.create(ObjectMapper.getInstance().map(airlineCompanyDTO, AirlineCompany.class));
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().map(result, AirlineCompanyDTO.class), CREATE_TITLE, CREATE_SUCCESS_MESSAGE), HttpStatus.OK);
    }

    @GetMapping(value = "read/{id}")
    public ResponseEntity<RestResponse<AirlineCompanyDTO>> read(@PathVariable("id") Integer id) {
        AirlineCompany result = airlineCompanyService.read(id);
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().map(result, AirlineCompanyDTO.class), READ_TITLE, READ_SUCCESS_MESSAGE), HttpStatus.OK);
    }

    @GetMapping(value = "readAll")
    public ResponseEntity<RestResponse<List<AirlineCompanyDTO>>> readAll() {
        List<AirlineCompany> result = airlineCompanyService.readAll();
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().mapAll(result, AirlineCompanyDTO.class), READ_LIST_TITLE, READ_LIST_SUCCESS_MESSAGE), HttpStatus.OK);
    }

    @PutMapping(value = "update")
    public ResponseEntity<RestResponse<AirlineCompanyDTO>> update(@RequestBody AirlineCompanyDTO airlineCompanyDTO) {
        AirlineCompany result = airlineCompanyService.update(ObjectMapper.getInstance().map(airlineCompanyDTO, AirlineCompany.class));
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().map(result, AirlineCompanyDTO.class), UPDATE_TITLE, UPDATE_SUCCESS_MESSAGE), HttpStatus.OK);
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<RestResponse<AirlineCompanyDTO>> delete(@PathVariable("id") Integer id) {
        AirlineCompany result = airlineCompanyService.delete(id);
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().map(result, AirlineCompanyDTO.class), DELETE_TITLE, DELETE_SUCCESS_MESSAGE), HttpStatus.OK);
    }
}
