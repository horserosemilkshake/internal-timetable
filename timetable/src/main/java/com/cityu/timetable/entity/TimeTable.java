package com.cityu.timetable.entity;

import com.cityu.timetable.service.BookingListServiceImpl;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ToString
public class TimeTable {
    @Bean
    public TimeTable createTimeTable(){
        return new TimeTable();
    }
    @Getter @Setter
    private List<Chart> table;
}
