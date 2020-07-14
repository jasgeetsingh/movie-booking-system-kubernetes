package com.mbs.bookingservice.controller;


import com.mbs.bookingservice.model.BookingDao;
import com.mbs.bookingservice.service.AdminServiceExchangeProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/bookings")
public class BookingController {

     Logger logger = LoggerFactory.getLogger(this.getClass());

     @Autowired
     AdminServiceExchangeProxy adminServiceExchange;

    @PostMapping("/new")
    public BookingDao addNewBooking(@RequestBody BookingDao bookingDao) {
        logger.info("Reach here BookingController Booking Service");
        return adminServiceExchange.requestBooking(bookingDao);
    }



}
