package com.cityu.timetable.controller;

import com.cityu.timetable.entity.TimeTable;
import com.cityu.timetable.service.TimeTableServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.DateUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Controller Class
 * @author Eric Tsoi
 * @version 1.0
 */

@Controller
@RequestMapping(path="/timetable")
public class MainController {
    @Autowired
    private TimeTableServiceImpl timeTableServiceImpl;
    private List<String> listOfRooms = new LinkedList<String>(Arrays.asList("LAU:17-201", "MPL:2212"));

    /**
     * @param model the model object
     * @param month the yyyy-mm string
     * @return the page with time table
     */
    @GetMapping("/{month}")
    public String setTimeTable(Model model, @PathVariable String month){
        //List<String> listOfRooms = new LinkedList<String>(Arrays.asList("LAU:17-201", "MPL:2212", "Sample Room", "Sample Room", "Sample Room", "Sample Room", "Sample Room", "Sample Room", "Sample Room"));

        model.addAttribute("products", timeTableServiceImpl.provideChart(listOfRooms, month));
        List <String> monthsForSelection = new LinkedList<>(Arrays.asList());
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM");
        for (int i = 0; i < 7; i++) {
            monthsForSelection.add(ft.format(dNow));
            Calendar myCal = Calendar.getInstance();
            myCal.setTime(dNow);
            myCal.add(Calendar.MONTH, +1);
            dNow = myCal.getTime();
        }
        model.addAttribute("months", monthsForSelection);
        model.addAttribute("currentMonth", month);
        return "mainpage";
    }

    /**
     * @return Error Page
     * */
    @GetMapping("/error")
    public String errorPage(){
        return "error";
    }
}