package com.example.paygoal.dtos;

import java.time.LocalDate;

public class ErrorResponse {
    private Integer status;
    private String message;
    private LocalDate localDate;

    public ErrorResponse() {
    }

    public ErrorResponse(Integer status, String message, LocalDate localDate) {
        this.status = status;
        this.message = message;
        this.localDate = localDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
