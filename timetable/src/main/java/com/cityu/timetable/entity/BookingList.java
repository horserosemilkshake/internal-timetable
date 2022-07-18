package com.cityu.timetable.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ToString
public class BookingList {
    @Bean
    public BookingList createBookingList(){
        return new BookingList();
    }
    @Getter @Setter
    private String room_id;

    @Getter @Setter
    private List<String> bookings;
}
