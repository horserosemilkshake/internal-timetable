package com.cityu.timetable.controller;

import com.cityu.timetable.entity.TimeTable;
import com.cityu.timetable.service.TimeTableServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping(path="/timetable")
public class MainController {
    @Autowired
    private TimeTableServiceImpl timeTableServiceImpl;
    @GetMapping("/{month}")
    public String setTimeTable(Model model, @PathVariable String month){
        List<String> listOfRooms = new LinkedList<String>(Arrays.asList("LAU:16-275", "LAU:17-201", "MPL:2212"));
        model.addAttribute("products", timeTableServiceImpl.provideChart(listOfRooms, month));
        return "mainpage";
    }
}