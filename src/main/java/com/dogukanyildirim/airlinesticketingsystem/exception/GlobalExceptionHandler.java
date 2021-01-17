package com.dogukanyildirim.airlinesticketingsystem.exception;

import com.dogukanyildirim.airlinesticketingsystem.dto.ExceptionHandlerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.dogukanyildirim.airlinesticketingsystem.constant.ExceptionMessages.UNEXPECTED_ERROR;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleConflict(Exception ex) {
        LOGGER.error("Exception Handler:", ex);
        ExceptionHandlerDTO exceptionHandlerDTO = new ExceptionHandlerDTO(ex.getMessage(), ex.getLocalizedMessage());

        if (ex instanceof ServiceException) {
            ServiceException se = (ServiceException) ex;
            exceptionHandlerDTO.setWarning(se.getWarning());
        } else {
            Throwable throwable = new ServiceException(UNEXPECTED_ERROR);
            exceptionHandlerDTO.setMessage(throwable.getMessage());
            exceptionHandlerDTO.setLocalizedMessage(ex.getLocalizedMessage());
        }
        exceptionHandlerDTO.setStatus(HttpStatus.EXPECTATION_FAILED);
        return new ResponseEntity<>(exceptionHandlerDTO, exceptionHandlerDTO.getStatus());

    }
}
