package com.isaa.cerda.picoplaca.controller;

import com.isaa.cerda.picoplaca.exception.InvalidPlateException;
import com.isaa.cerda.picoplaca.exception.PastDateTimeException;
import com.isaa.cerda.picoplaca.model.PicoPlacaDto;
import com.isaa.cerda.picoplaca.services.PicoPlacaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/picoplaca")
@CrossOrigin(origins = "http://localhost:4200")
public class PicoPlacaController {

    private final PicoPlacaService picoPlacaService;

    public PicoPlacaController(PicoPlacaService picoPlacaService) {
        this.picoPlacaService = picoPlacaService;
    }

    @GetMapping("/allowed")
    public boolean isAllowed(
        @RequestParam String placa,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime hora
    ) {
        if (!placa.matches("^[A-Za-z]{3}\\d{4}$")) {
            throw new InvalidPlateException("La placa debe tener el formato ABC1234");
        }

        LocalDateTime requested = LocalDateTime.of(fecha, hora);
        if (requested.isBefore(LocalDateTime.now())) {
            throw new PastDateTimeException("La fecha y hora no pueden ser anteriores a la actual");
        }

        PicoPlacaDto dto = new PicoPlacaDto(fecha, hora, placa);
        return picoPlacaService.isRoadCirculationAllowed(dto);
    }
}
