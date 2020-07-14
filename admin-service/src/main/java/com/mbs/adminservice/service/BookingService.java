package com.mbs.adminservice.service;


import com.mbs.adminservice.exception.HouseFullException;
import com.mbs.adminservice.model.Booking;
import com.mbs.adminservice.model.dao.BookingDao;

import java.util.List;


public interface BookingService {
    List<Booking> getAllBookings();

    Booking getBookingById(Long booking_id);

    BookingDao newBooking(BookingDao newBooking) throws HouseFullException;

    Booking updateBooking(Booking updatedBooking, Long booking_id);

    void deleteBookingById(Long booking_id);
}

