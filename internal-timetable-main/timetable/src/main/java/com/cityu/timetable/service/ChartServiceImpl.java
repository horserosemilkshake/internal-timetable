package com.cityu.timetable.service;

import com.cityu.timetable.entity.BookingList;
import com.cityu.timetable.entity.Chart;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ChartServiceImpl implements ChartService {
    public Chart provideChart(BookingList bookings, String month){
        Chart temp = new Chart();
        temp.setRoom_id(bookings.getRoom_id());

        List<Integer> bigMonths = new LinkedList<Integer>(Arrays.asList(1, 3, 5, 7, 8, 10, 12));
        List<Boolean> chart = new LinkedList<Boolean>();

        if (bigMonths.contains(Integer.parseInt(month))){
            for (int i = 0; i < 31; i++){
                chart.add(false);
            }
        }
        else {
            for (int i = 0; i < 30; i++){
                chart.add(false);
            }
        }
        bookings.getBookings().forEach(x -> chart.set(Integer.parseInt(x.substring(8, 10)), true));

        temp.setChart(chart);
        return temp;
    }
}
