package com.mbs.adminservice.service;


import com.mbs.adminservice.model.Seat;

import java.util.List;

public interface SeatService {

    List<Seat> getAllSeats();

    Seat getSeatById(Long seat_id);

    Seat addSeat(Seat newSeat);

    Seat updateSeat(Seat updatedSeat, Long seat_id);

    void deleteSeatById(Long seat_id);
}
