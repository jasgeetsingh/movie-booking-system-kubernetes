package com.mbs.adminservice.controller;


import com.mbs.adminservice.model.SeatBooked;
import com.mbs.adminservice.service.SeatBookedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SeatBookedController {

    @Autowired
    SeatBookedService seatBookedService;


    @GetMapping("/seatBookedList")
    public List<SeatBooked> getAllSeatBooked() {
        return seatBookedService.getAllSeatBooked();
    }
}
