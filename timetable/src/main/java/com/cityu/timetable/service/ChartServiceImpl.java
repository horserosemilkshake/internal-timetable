package com.cityu.timetable.service;

import com.cityu.timetable.entity.BookingList;
import com.cityu.timetable.entity.Chart;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Component
@Configuration
public class ChartServiceImpl implements ChartService {
    @Bean
    public ChartServiceImpl createChartServiceImpl(){
        return new ChartServiceImpl();
    }
    public Chart provideChart(BookingList bookings, String month){
        Chart temp = new Chart();
        temp.setRoom_id(bookings.getRoom_id());

        List<Integer> bigMonths = new LinkedList<Integer>(Arrays.asList(1, 3, 5, 7, 8, 10, 12));
        List<Boolean> blanks = new LinkedList<Boolean>();

        if (bigMonths.contains(Integer.parseInt(month))){
            for (int i = 0; i < 31; i++){
                blanks.add(false);
            }
        }
        else {
            for (int i = 0; i < 30; i++){
                blanks.add(false);
            }
        }
        bookings.getBookings().forEach(x -> blanks.set(Integer.parseInt(x.substring(8, 10)), true));

        temp.setChart(blanks);
        return temp;
    }
}
