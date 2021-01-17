package com.dogukanyildirim.airlinesticketingsystem.controller;

import com.dogukanyildirim.airlinesticketingsystem.config.JwtUtil;
import com.dogukanyildirim.airlinesticketingsystem.dto.LoginDTO;
import com.dogukanyildirim.airlinesticketingsystem.dto.RestResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.dogukanyildirim.airlinesticketingsystem.constant.ResponseMessages.*;
import static com.dogukanyildirim.airlinesticketingsystem.enums.UserRoleEnum.ADMIN;
import static com.dogukanyildirim.airlinesticketingsystem.enums.UserRoleEnum.PASSENGER;

@RequestMapping("/login")
@RestController
public class LoginController {

    @Value("${system.admin.user}")
    String user;
    @Value("${system.admin.pass}")
    String pass;

    private final JwtUtil jwtUtil;

    public LoginController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping(value = "/loginAsAdmin")
    public ResponseEntity<RestResponse<String>> loginAsAdmin() {
        String token = jwtUtil.generateJwtToken(user, ("ROLE_" + ADMIN.getRoleName()));
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        return new ResponseEntity<>(new RestResponse<>(LOGIN_TITLE, ADMIN_LOGIN_MESSAGE), headers, HttpStatus.OK);
    }

    @PostMapping(value = "/loginAsPassenger")
    public ResponseEntity<RestResponse<String>> loginAsPassenger(@RequestBody LoginDTO loginDTO) {
        String token = jwtUtil.generateJwtToken(loginDTO.getUsername(), ("ROLE_" + PASSENGER.getRoleName()));
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        return new ResponseEntity<>(new RestResponse<>(LOGIN_TITLE, LOGIN_MESSAGE), headers, HttpStatus.OK);
    }

}


