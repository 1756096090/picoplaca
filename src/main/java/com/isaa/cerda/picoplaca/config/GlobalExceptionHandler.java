package com.isaa.cerda.picoplaca.config;

import com.isaa.cerda.picoplaca.model.ErrorResponse;
import com.isaa.cerda.picoplaca.exception.InvalidPlateException;
import com.isaa.cerda.picoplaca.exception.PastDateTimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidPlateException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPlate(InvalidPlateException ex) {
        ErrorResponse body = new ErrorResponse(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(body);
    }

    @ExceptionHandler(PastDateTimeException.class)
    public ResponseEntity<ErrorResponse> handlePastDateTime(PastDateTimeException ex) {
        ErrorResponse body = new ErrorResponse(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(body);
    }

    // Si quisieras un catch-all:
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAny(Exception ex) {
        ErrorResponse body = new ErrorResponse("Error interno");
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(body);
    }
}
