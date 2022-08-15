package com.cityu.timetable.service;

import com.cityu.timetable.entity.BookingList;
import com.cityu.timetable.entity.Chart;

public interface ChartService {
    Chart provideChart(BookingList bookings, String month);
}
