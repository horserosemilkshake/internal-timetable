package com.cityu.timetable.service;

import com.cityu.timetable.entity.Chart;
import com.cityu.timetable.entity.TimeTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Configuration
@Component
public class TimeTableServiceImpl implements TimeTableService {
    @Autowired
    private BookingListService bookingListServiceImpl;
    @Autowired
    private TimeTable timeTable;
    @Autowired
    private ChartService chartServiceImpl;

    private List<Chart> tempTimeTable;

    @Autowired
    public TimeTableServiceImpl() {
        this.tempTimeTable = new LinkedList<>();
    }

    public TimeTable provideChart(List<String> rooms, String month){
        tempTimeTable.clear();
        for (int i = 0; i < rooms.size(); i++) { tempTimeTable.add(chartServiceImpl.provideChart(bookingListServiceImpl.getBookingByMonth(rooms.get(i), month), month)); }
        timeTable.setTable(tempTimeTable);
        return timeTable;
    }
}
