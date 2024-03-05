package com.example.springexceptionhandlingandvalidations.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProcessingException extends  Exception{
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public ProcessingException(String message) {
        this.message = message;
        this.code = code;
    }

    private String message;
    private Long code;
}
