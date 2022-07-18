package com.cityu.timetable.service;

import com.cityu.timetable.base.BaseConstants;
import com.cityu.timetable.entity.BookingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Configuration
public class BookingListServiceImpl implements BookingListService {
    @Bean
    public BookingListServiceImpl createBookingListServiceImpl(){
        return new BookingListServiceImpl();
    }
    private BookingList getUnprocessedBookingList(String room_id){
        String url = BaseConstants.Protocol.concat(BaseConstants.ApiHost)
                .concat(BaseConstants.ApiPrefix)
                .concat("/rooms/")
                .concat(room_id);

        HttpHeaders headers = new HttpHeaders();
        headers.set("client_id", BaseConstants.ApiClientId);
        headers.set("client_secret", BaseConstants.ApiClientSecret);

        ResponseEntity<String> response = null;
        try {
            HttpEntity entity = new HttpEntity(headers);
            RestTemplate restTemplate = new RestTemplate();
            response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        } catch (HttpServerErrorException e) {
            e.printStackTrace();
        }

        BookingList bookingList = new BookingList();
        bookingList.setRoom_id(room_id);
        try {
            JSONObject jObject = new JSONObject(response.getBody());
            JSONArray jArray = jObject.getJSONArray("bookings");
            if (jArray != null) {
                List<String> tempList = new LinkedList<String>();
                for (int i = 0; i < jArray.length(); i++){
                    JSONObject jsonLineItem = (JSONObject) jArray.getJSONObject(i);
                    tempList.add(jsonLineItem.getString("start_dt"));
                }
                bookingList.setBookings(tempList);
            } else {
                bookingList.setBookings(new LinkedList<>());
            }
        } catch (JSONException err) {
            err.printStackTrace();
        }

        return bookingList;
    }

    public BookingList getBookingByMonth(String room_id, String month) {
        BookingList bookingList = getUnprocessedBookingList(room_id);
        List<String> tempBookingList = bookingList.getBookings().stream().filter(x -> Integer.parseInt(x.substring(5, 7)) == Integer.parseInt(month)).collect(Collectors.toList());
        bookingList.setBookings(tempBookingList);
        return bookingList;
    }

    public String getAllBooking(String room_id) {
        BookingList bookingList = getUnprocessedBookingList(room_id);
        return bookingList.toString();
    }
}
