package com.isaa.cerda.picoplaca.model;

import java.time.LocalDateTime;

public class ErrorResponse {
    private LocalDateTime timestamp;
    private String message;

    public ErrorResponse(String message) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public String getMessage()   { return message;   }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public void setMessage(String message)           { this.message = message;   }
}
