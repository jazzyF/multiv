package com.fola.model;

import java.util.ArrayList;
import java.util.List;

public class ResourceResponse {

    private List<String> errors = new ArrayList<>();
    private int code;
    private String message;

    public ResourceResponse() {
        code = -1;
    }

    public ResourceResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }
}
