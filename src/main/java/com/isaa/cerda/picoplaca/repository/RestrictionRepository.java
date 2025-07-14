package com.isaa.cerda.picoplaca.repository;

import com.isaa.cerda.picoplaca.model.DayRestriction;
import com.isaa.cerda.picoplaca.model.TimeRestriction;

import java.time.DayOfWeek;
import java.time.LocalTime;


public interface RestrictionRepository {


    DayRestriction getDayRestriction(final DayOfWeek day);

    TimeRestriction getTimeRestriction(final LocalTime time);

    boolean isRestricted(final DayOfWeek day,
                         final int       digit,
                         final LocalTime time);
}
