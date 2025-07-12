package com.isaa.cerda.picoplaca.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class PicoPlacaDto {

    private LocalDate fecha;
    private LocalTime hora;
    private String placa;

    public PicoPlacaDto() {}

    public PicoPlacaDto(LocalDate fecha, LocalTime hora, String placa) {
        this.fecha = fecha;
        this.hora = hora;
        this.placa = placa;
    }

    // Getters y setters
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }


}
