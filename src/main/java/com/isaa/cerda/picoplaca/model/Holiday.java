package com.isaa.cerda.picoplaca.model;

import java.time.LocalDate;

public class Holiday {
    private final LocalDate date;
  

    public Holiday(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

}
