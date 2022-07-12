package com.cityu.timetable.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@ToString
public class BookingList {
    @JsonProperty("room_id")
    @Getter @Setter
    private String room_id;

    @JsonProperty("bookings")
    @Getter @Setter
    private List<String> bookings;
}
