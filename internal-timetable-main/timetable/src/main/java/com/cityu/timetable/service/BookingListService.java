package com.cityu.timetable.service;

import com.cityu.timetable.entity.BookingList;

public interface BookingListService {
    abstract BookingList getBookingByMonth(String room_id, String month);
    abstract String getAllBooking(String room_id);
}
