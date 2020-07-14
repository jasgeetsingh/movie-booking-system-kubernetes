package com.mbs.adminservice.controller;


import com.mbs.adminservice.exception.HouseFullException;
import com.mbs.adminservice.model.Booking;
import com.mbs.adminservice.model.dao.BookingDao;
import com.mbs.adminservice.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/bookings")
public class BookingController {

    @Autowired
    BookingService bookingService;

     Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/all")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewBooking(@RequestBody BookingDao bookingDao) throws HouseFullException {

        return ResponseEntity.status(HttpStatus.OK).body(bookingService.newBooking(bookingDao));
    }
}
