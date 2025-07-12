package com.isaa.cerda.picoplaca.services;

import com.isaa.cerda.picoplaca.model.PicoPlacaDto;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class PicoPlacaServiceImpl implements PicoPlacaService {

    private static final LocalTime MORNING_START  = LocalTime.of(6, 0);
    private static final LocalTime MORNING_END    = LocalTime.of(9, 30);
    private static final LocalTime EVENING_START  = LocalTime.of(16, 0);
    private static final LocalTime EVENING_END    = LocalTime.of(20, 0);

    private static final Map<DayOfWeek, List<Integer>> RESTRICTIONS = Map.of(
        DayOfWeek.MONDAY,    List.of(1, 2),
        DayOfWeek.TUESDAY,   List.of(3, 4),
        DayOfWeek.WEDNESDAY, List.of(5, 6),
        DayOfWeek.THURSDAY,  List.of(7, 8),
        DayOfWeek.FRIDAY,    List.of(9, 0)
    );

    private int extractLastDigit(String placa) {
        String digits = placa.replaceAll("\\D+", "");
        if (digits.isEmpty())
            throw new IllegalArgumentException("La placa no contiene dígitos");
        return Character.getNumericValue(digits.charAt(digits.length() - 1));
    }

    private boolean isInRestrictedTime(LocalTime time) {
        return (!time.isBefore(MORNING_START) && !time.isAfter(MORNING_END))
            || (!time.isBefore(EVENING_START) && !time.isAfter(EVENING_END));
    }

    @Override
    public Boolean isRoadCirculationAllowed(PicoPlacaDto dto) {
        var day   = dto.getFecha().getDayOfWeek();
        var time  = dto.getHora();
        var digit = extractLastDigit(dto.getPlaca());

        var restrictedDigits = RESTRICTIONS.getOrDefault(day, Collections.emptyList());
        if (restrictedDigits.contains(digit) && isInRestrictedTime(time)) {
            return false;
        }
        return true;
    }
}
