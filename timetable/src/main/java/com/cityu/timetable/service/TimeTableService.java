package com.cityu.timetable.service;

import com.cityu.timetable.entity.TimeTable;

import java.util.List;

public interface TimeTableService {
    TimeTable provideChart(List<String> rooms, String month);
}
