package com.dogukanyildirim.airlinesticketingsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestResponse<T> {
    private T data;
    private String title;
    private String message;

    public RestResponse() {
    }

    public RestResponse(T data) {
        this.data = data;
    }

    public RestResponse(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public RestResponse(T data, String title, String message) {
        this.data = data;
        this.title = title;
        this.message = message;
    }

}

