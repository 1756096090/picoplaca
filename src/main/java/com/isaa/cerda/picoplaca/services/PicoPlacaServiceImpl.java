package com.isaa.cerda.picoplaca.services;

import com.isaa.cerda.picoplaca.model.PicoPlacaDto;
import com.isaa.cerda.picoplaca.repository.RestrictionRepository;
import com.isaa.cerda.picoplaca.repository.HolidayRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Service
public class PicoPlacaServiceImpl implements PicoPlacaService {

    private final RestrictionRepository restrictionRepository;
    private final HolidayRepository      holidayRepository;

    public PicoPlacaServiceImpl(final RestrictionRepository restrictionRepository,
                                final HolidayRepository      holidayRepository) {
                                    
        this.restrictionRepository = Objects.requireNonNull(restrictionRepository, "restrictionRepository no puede ser null");
        this.holidayRepository     = Objects.requireNonNull(holidayRepository,     "holidayRepository no puede ser null");
    }

    @Override
    public boolean isRoadCirculationAllowed(final PicoPlacaDto request) {
        LocalDate date    = request.getFecha();
        LocalTime time    = request.getHora();
        DayOfWeek day     = date.getDayOfWeek();
        int lastDigit     = extractLastDigit(request.getPlaca());

        if (holidayRepository.isHoliday(date)) {
            return true;
        }

        return !restrictionRepository.isRestricted(day, lastDigit, time);
    }

    private int extractLastDigit(final String plate) {
        String digits = plate.replaceAll("\\D+", "");
        if (digits.isEmpty()) {
            throw new IllegalArgumentException("La placa no contiene dígitos");
        }
        return Character.getNumericValue(digits.charAt(digits.length() - 1));
    }
}
