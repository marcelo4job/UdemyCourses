package com.marcelo.cursomc.resources.exception;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class StandardError implements Serializable {

    private static final long serialVersionUID = 1L;

    private HttpStatus status;
    private String msg;
    private Long timeStamp;


    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public StandardError(HttpStatus status, String msg, Long timeStamp) {
        this.status = status;
        this.msg = msg;
        this.timeStamp = timeStamp;
    }
}
