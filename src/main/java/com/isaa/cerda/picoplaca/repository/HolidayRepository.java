package com.isaa.cerda.picoplaca.repository;

import java.time.LocalDate;

public interface HolidayRepository {
    
    boolean isHoliday(LocalDate date);
}
