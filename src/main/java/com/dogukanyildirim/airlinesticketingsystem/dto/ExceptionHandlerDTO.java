package com.dogukanyildirim.airlinesticketingsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionHandlerDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String localizedMessage;
    private HttpStatus status;
    private Boolean warning;

    private ExceptionHandlerDTO() {
        timestamp = LocalDateTime.now();
        warning = false;
    }

    public ExceptionHandlerDTO(String message, String localizedMessage) {
        this();
        this.message = message;
        this.localizedMessage = localizedMessage;
    }

    public ExceptionHandlerDTO(String message, HttpStatus status) {
        this();
        this.message = message;
        this.status = status;
    }

    public ExceptionHandlerDTO(String message, HttpStatus status, String localizedMessage) {
        this();
        this.message = message;
        this.status = status;
        this.localizedMessage = localizedMessage;
    }
}
