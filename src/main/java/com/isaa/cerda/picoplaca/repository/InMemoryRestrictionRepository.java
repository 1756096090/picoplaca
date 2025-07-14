package com.isaa.cerda.picoplaca.repository;

import com.isaa.cerda.picoplaca.model.DayRestriction;
import com.isaa.cerda.picoplaca.model.TimeRestriction;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Repository
public class InMemoryRestrictionRepository implements RestrictionRepository {

    private static final List<DayRestriction> DAY_RESTRICTIONS = List.of(
        new DayRestriction(DayOfWeek.MONDAY,    List.of(1, 2)),
        new DayRestriction(DayOfWeek.TUESDAY,   List.of(3, 4)),
        new DayRestriction(DayOfWeek.WEDNESDAY, List.of(5, 6)),
        new DayRestriction(DayOfWeek.THURSDAY,  List.of(7, 8)),
        new DayRestriction(DayOfWeek.FRIDAY,    List.of(9, 0))
    );

    private static final List<TimeRestriction> TIME_RESTRICTIONS = List.of(
        new TimeRestriction(LocalTime.of(6,  0), LocalTime.of(9,  30)),
        new TimeRestriction(LocalTime.of(16, 0), LocalTime.of(20, 0))
    );

    @Override
    public DayRestriction getDayRestriction(final DayOfWeek day) {
        for (DayRestriction dr : DAY_RESTRICTIONS) {
            if (dr.getDay() == day) {
                return dr;
            }
        }
        return new DayRestriction(day, List.of());
    }

    @Override
    public TimeRestriction getTimeRestriction(final LocalTime time) {
        for (TimeRestriction tr : TIME_RESTRICTIONS) {
            if (tr.includes(time)) {
                return tr;
            }
        }
        return null;
    }

    @Override
    public boolean isRestricted(final DayOfWeek day,
                                final int       digit,
                                final LocalTime time) {
        DayRestriction dayRule = getDayRestriction(day);
        if (!dayRule.containsDigit(digit)) {
            return false;
        }

        TimeRestriction window = getTimeRestriction(time);
        return window != null;
    }
}
