package com.cityu.timetable.service;

import com.cityu.timetable.entity.BookingList;
import com.cityu.timetable.entity.Chart;
import org.springframework.beans.factory.annotation.Autowired;
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
    //private List<Integer> bigMonths = new LinkedList<Integer>(Arrays.asList(1, 3, 5, 7, 8, 10, 12));
    //private List<Boolean> blanks = new LinkedList<Boolean>();
    //private Chart temp = new Chart();
    private List<Integer> bigMonths;
    private List<Boolean> blanks;
    private Chart temp;
    @Autowired
    public ChartServiceImpl(){
        this.bigMonths = new LinkedList<>(Arrays.asList(1, 3, 5, 7, 8, 10, 12));
        //this.blanks = new LinkedList<>();
        this.temp = Chart.createChart();
    }
    public Chart provideChart(BookingList bookings, String month){
        blanks = new LinkedList<>();
        temp = Chart.createChart();
        temp.setRoom_id(bookings.getRoom_id());

        if (bigMonths.contains(Integer.parseInt(month.split("\\.")[1]))){
            for (int i = 0; i < 31; i++){
                blanks.add(false);
            }
        }
        else {
            int year = Integer.parseInt(month.split("\\.")[0].toString());
            if (Integer.parseInt(month.split("\\.")[1]) == 2) {
                if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
                    for (int i = 0; i < 29; i++){
                        blanks.add(false);
                    }
                }else{
                    for (int i = 0; i < 28; i++){
                        blanks.add(false);
                    }
                }
            }
            else {
                for (int i = 0; i < 30; i++){
                    blanks.add(false);
                }
            }
        }
        bookings.getBookings().forEach(x -> blanks.set(Integer.parseInt(x.substring(8, 10)) - 1, true));
        temp.setChart(blanks);
        return temp;
    }
}
