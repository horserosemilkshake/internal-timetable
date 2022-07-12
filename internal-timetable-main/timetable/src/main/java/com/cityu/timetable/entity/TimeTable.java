package com.cityu.timetable.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
public class TimeTable {
    @Getter @Setter
    private List<Chart> table;
}
