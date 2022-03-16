package com.gromyk.projectinfo.rest.responses;

public class ErrorResponse {
    private String cause;
    private Long timestamp;
    private String description;
    private int status;

    public ErrorResponse() {
        this.timestamp = System.currentTimeMillis();
    }

    public ErrorResponse(String cause, String description, int status) {
        this.cause = cause;
        this.timestamp = System.currentTimeMillis();
        this.description = description;
        this.status = status;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
