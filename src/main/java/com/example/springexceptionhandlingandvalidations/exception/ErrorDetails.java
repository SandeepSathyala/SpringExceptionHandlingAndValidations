package com.example.springexceptionhandlingandvalidations.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorDetails {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Long statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public ErrorDetails(String message, Long statusCode, String responseMsg) {
        this.message = message;
        this.statusCode = statusCode;
        this.responseMsg = responseMsg;
    }

    private String message;
    private Long statusCode;
    private  String responseMsg;
}
