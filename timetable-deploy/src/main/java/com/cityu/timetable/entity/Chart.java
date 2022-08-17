package com.cityu.timetable.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ToString
public class Chart {
    @Bean
    public static Chart createChart(){
        return new Chart();
    }
    @Getter @Setter
    private String room_id;
    @Getter @Setter
    private List<Boolean> chart;
}

