package com.cityu.timetable.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
public class Chart {
    @Getter @Setter
    private String room_id;
    @Getter @Setter
    private List<Boolean> chart;
}

