package com.mbs.adminservice.model.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDao {
    Long movieId;  // which movie
    Long screenId; // which screen
    Long userId;   // who want to book the tickets
    Integer seatRequired; // how many seat wanted to book
    String message;
}
