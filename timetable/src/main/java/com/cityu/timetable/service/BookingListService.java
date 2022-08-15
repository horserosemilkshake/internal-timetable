package com.cityu.timetable.service;

import com.cityu.timetable.entity.BookingList;

public interface BookingListService {
    BookingList getBookingByMonth(String room_id, String month);
    String getAllBooking(String room_id);
}
