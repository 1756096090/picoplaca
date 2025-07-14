package com.isaa.cerda.picoplaca.model;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Objects;

public class DayRestriction {
    private final DayOfWeek day;
    private final List<Integer> digits;

    public DayRestriction(DayOfWeek day, List<Integer> digits) {
        this.day    = Objects.requireNonNull(day);
        this.digits = Objects.requireNonNull(digits);
    }

    public DayOfWeek getDay() {
        return day;
    }

    public List<Integer> getDigits() {
        return List.copyOf(digits);
    }


    public boolean containsDigit(int digit) {
        return digits.contains(digit);
    }
}
