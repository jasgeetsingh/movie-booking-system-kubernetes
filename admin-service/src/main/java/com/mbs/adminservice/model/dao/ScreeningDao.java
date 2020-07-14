package com.mbs.adminservice.model.dao;

import com.mbs.adminservice.model.Screening;
import com.mbs.adminservice.model.Seat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ScreeningDao {

    List<Seat> remainingSeats;
    Screening screening;

}
