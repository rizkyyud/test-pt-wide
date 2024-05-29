package com.testing.wide.tecnologies.demo_test.helpers;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomResponse<T> {
    private String status;
    private String message;
    private T data;
    private int statusCode;

    public CustomResponse() {

    }

    public CustomResponse(String status, String message, T data, int statusCode) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.statusCode = statusCode;
    }
}
