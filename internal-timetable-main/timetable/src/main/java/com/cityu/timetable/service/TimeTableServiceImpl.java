package com.cityu.timetable.service;

import com.cityu.timetable.entity.Chart;
import com.cityu.timetable.entity.TimeTable;

import java.util.LinkedList;
import java.util.List;

public class TimeTableServiceImpl implements TimeTableService{
    public TimeTable provideChart(List<String> rooms, String month){
        TimeTable timeTable = new TimeTable();
        List<Chart> tempTimeTable = new LinkedList<Chart>();
        for (int i = 0; i < rooms.size(); i++) {
            BookingListServiceImpl bookingListServiceImpl = new BookingListServiceImpl();
            ChartServiceImpl chartServiceImpl = new ChartServiceImpl();
            tempTimeTable.add(chartServiceImpl.provideChart(bookingListServiceImpl.getBookingByMonth(rooms.get(i), month), month));
        }
        timeTable.setTable(tempTimeTable);
        return timeTable;
    }
}
