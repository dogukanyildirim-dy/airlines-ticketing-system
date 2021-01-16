package com.dogukanyildirim.airlinesticketingsystem.exception;

public class ServiceException extends RuntimeException {
    private final Boolean warning;

    public ServiceException(String message) {
        super(message);
        this.warning = Boolean.FALSE;
    }
    public ServiceException(String message, boolean warning) {
        super(message);
        this.warning = warning;
    }
    public Boolean getWarning() {
        return this.warning;
    }
}
