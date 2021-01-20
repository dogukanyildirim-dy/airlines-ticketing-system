package com.dogukanyildirim.airlinesticketingsystem.controller;

import com.dogukanyildirim.airlinesticketingsystem.domain.management.Route;
import com.dogukanyildirim.airlinesticketingsystem.dto.RestResponse;
import com.dogukanyildirim.airlinesticketingsystem.dto.RouteDTO;
import com.dogukanyildirim.airlinesticketingsystem.dto.mapper.ObjectMapper;
import com.dogukanyildirim.airlinesticketingsystem.service.RouteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dogukanyildirim.airlinesticketingsystem.constant.ResponseMessages.*;

/**
 * Bu controller rota CRUD işlemlerine yönelik entpointlerin bulunduğu controllerdır.
 *
 * @author dogukan.yildirim
 */

@RequestMapping(value = "route")
@RestController
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping(value = "create")
    public ResponseEntity<RestResponse<RouteDTO>> create(@RequestBody RouteDTO routeDTO) {
        Route result = routeService.create(ObjectMapper.getInstance().map(routeDTO, Route.class));
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().map(result, RouteDTO.class), CREATE_TITLE, CREATE_SUCCESS_MESSAGE), HttpStatus.OK);
    }

    @GetMapping(value = "read/{id}")
    public ResponseEntity<RestResponse<RouteDTO>> read(@PathVariable("id") Integer id) {
        Route result = routeService.read(id);
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().map(result, RouteDTO.class), READ_TITLE, READ_SUCCESS_MESSAGE), HttpStatus.OK);
    }

    @GetMapping(value = "readByIataCode/{iataCode}")
    public ResponseEntity<RestResponse<List<RouteDTO>>> read(@PathVariable("iataCode") String iataCode) {
        List<Route> result = routeService.readBySourceIata(iataCode);
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().mapAll(result, RouteDTO.class), READ_TITLE, READ_SUCCESS_MESSAGE), HttpStatus.OK);
    }

    @GetMapping(value = "readAll")
    public ResponseEntity<RestResponse<List<RouteDTO>>> readAll() {
        List<Route> result = routeService.readAll();
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().mapAll(result, RouteDTO.class), READ_LIST_TITLE, READ_LIST_SUCCESS_MESSAGE), HttpStatus.OK);
    }

    @PutMapping(value = "update")
    public ResponseEntity<RestResponse<RouteDTO>> update(@RequestBody RouteDTO routeDTO) {
        Route result = routeService.update(ObjectMapper.getInstance().map(routeDTO, Route.class));
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().map(result, RouteDTO.class), UPDATE_TITLE, UPDATE_SUCCESS_MESSAGE), HttpStatus.OK);
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<RestResponse<RouteDTO>> delete(@PathVariable("id") Integer id) {
        Route result = routeService.delete(id);
        return new ResponseEntity<>(new RestResponse<>(ObjectMapper.getInstance().map(result, RouteDTO.class), DELETE_TITLE, DELETE_SUCCESS_MESSAGE), HttpStatus.OK);
    }
}
