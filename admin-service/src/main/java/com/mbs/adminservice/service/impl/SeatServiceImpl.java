package com.mbs.adminservice.service.impl;


import com.mbs.adminservice.model.Seat;
import com.mbs.adminservice.repository.SeatRepository;
import com.mbs.adminservice.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class SeatServiceImpl implements SeatService {

    @Autowired
    SeatRepository seatRepository;


    @Override
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    @Override
    public Seat getSeatById(Long seat_id) {
        return null;
    }

    @Override
    public Seat addSeat(Seat newSeat) {
       return seatRepository.save(newSeat);
    }

    @Override
    public Seat updateSeat(Seat updatedSeat, Long seat_id) {
        return null;
    }

    @Override
    public void deleteSeatById(Long seat_id) {

    }
}
