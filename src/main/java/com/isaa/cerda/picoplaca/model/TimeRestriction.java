package com.isaa.cerda.picoplaca.model;

import java.time.LocalTime;
import java.util.Objects;

public class TimeRestriction {
    private final LocalTime start;
    private final LocalTime end;

    public TimeRestriction(LocalTime start, LocalTime end) {
        this.start = Objects.requireNonNull(start);
        this.end   = Objects.requireNonNull(end);
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("end debe ser ≥ start");
        }
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public boolean includes(LocalTime time) {
        return !time.isBefore(start) && !time.isAfter(end);
    }
}
