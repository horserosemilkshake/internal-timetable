package com.cityu.timetable.controller;

import com.cityu.timetable.entity.Chart;
import com.cityu.timetable.service.BookingListServiceImpl;
import com.cityu.timetable.service.ChartServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="api/v1/timetable/time-and-room/")
public class ApiController {
    @GetMapping("/{room_id}/{month}")
    public @ResponseBody String setTimeTable(@PathVariable String room_id, @PathVariable String month){
        BookingListServiceImpl bookingListServiceImpl = new BookingListServiceImpl();
        ChartServiceImpl chartServiceImpl = new ChartServiceImpl();
        return chartServiceImpl.provideChart(bookingListServiceImpl.getBookingByMonth(room_id, month), month).toString();
    }
}
