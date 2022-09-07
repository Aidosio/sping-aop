package com.example.spingaop.util;

import lombok.Data;

import java.util.Collection;

@Data
public class CustomResponse<T> {
    private int code;
    private String message;
    private Collection<T> responsesList;

    public CustomResponse(Collection<T> response, CustomStatus status) {
        this.responsesList = response;
        this.code = status.getCode();
        this.message = status.getMessage();
    }
}
