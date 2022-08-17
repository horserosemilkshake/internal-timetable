package com.cityu.timetable.service;

import com.cityu.timetable.base.BaseConstants;
import com.cityu.timetable.entity.BookingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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

    /**
     * This method gets booking availability record based on room_id.
     * @param room_id the room code (e.g., LAU:16-275)
     * @return Nothing.
     * @exception HttpServerErrorException On input error / external API error / network connectivity error.
     * @exception JSONException on JSON object parsing error / external API error.
     * @see HttpServerErrorException
     * @see JSONException
     */
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
        } catch (HttpServerErrorException err) {
            err.printStackTrace();
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

        /*
        BookingList bookingList = new BookingList();
        bookingList.setRoom_id(room_id);
        bookingList.setBookings(new LinkedList<>());*/

        return bookingList;
    }

    /**
     * This method filter retrieved booking records by months. This method depends on the getUnprocessedBookingList method.
     * @param room_id the room code (e.g., LAU:16-275)
     * @param room_id the room code (e.g., LAU:16-275)
     * @return Nothing.
     * @exception HttpServerErrorException On input error / external API error / network connectivity error.
     * @exception JSONException on JSON object parsing error / external API error.
     * @see HttpServerErrorException
     * @see JSONException
     */
    public BookingList getBookingByMonth(String room_id, String month) {
        BookingList bookingList = getUnprocessedBookingList(room_id);
        List<String> tempBookingList = bookingList.getBookings().stream().filter(x -> Integer.parseInt(x.substring(5, 7)) == Integer.parseInt(month.split("\\.")[1])).collect(Collectors.toList());
        bookingList.setBookings(tempBookingList);
        return bookingList;
    }

    public String getAllBooking(String room_id) {
        BookingList bookingList = getUnprocessedBookingList(room_id);
        return bookingList.toString();
    }
}
