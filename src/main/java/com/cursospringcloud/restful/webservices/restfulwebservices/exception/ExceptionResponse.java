package com.cursospringcloud.restful.webservices.restfulwebservices.exception;

import java.util.Date;

public class ExceptionResponse {

    private Date timestamp;

    private String message;

    private String detail;

    public ExceptionResponse(Date timestamp, String message, String detail) {
        this.timestamp = timestamp;
        this.message = message;
        this.detail = detail;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }
}
