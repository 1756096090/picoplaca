package com.isaa.cerda.picoplaca.repository;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class InMemoryHolidayRepository implements HolidayRepository {

    private static final List<LocalDate> HOLIDAYS = List.of(
        LocalDate.of(2025, 1, 1),
        LocalDate.of(2025, 3, 3),
        LocalDate.of(2025, 3, 4),
        LocalDate.of(2025, 4, 18),
        LocalDate.of(2025, 5, 2),
        LocalDate.of(2025, 5, 23),
        LocalDate.of(2025, 8, 11),
        LocalDate.of(2025, 10, 10),
        LocalDate.of(2025, 11, 3),
        LocalDate.of(2025, 11, 4),
        LocalDate.of(2025, 12, 25)
    );

    @Override
    public boolean isHoliday(LocalDate date) {
        return HOLIDAYS.contains(date);
    }
}
