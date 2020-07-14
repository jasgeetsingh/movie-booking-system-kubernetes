package com.mbs.adminservice.controller;


import com.mbs.adminservice.exception.HouseFullException;
import com.mbs.adminservice.model.Screening;
import com.mbs.adminservice.model.Seat;
import com.mbs.adminservice.model.dao.MovieScreenDao;
import com.mbs.adminservice.service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/screenings")
public class ScreeningController {

    @Autowired
    ScreeningService screeningService;


    @GetMapping("/all")
    public ResponseEntity<?> getAllScreenings(@RequestParam(required = false) Map<String, String> dates) {
        List<Screening> screenings = null;
        if(!dates.isEmpty()) {
            LocalDateTime startDate = LocalDateTime.parse(dates.get("startDate"));
            LocalDateTime endDate = LocalDateTime.parse(dates.get("endDate"));
            screenings = screeningService.getAllScreenings(startDate, endDate);
        } else {
            screenings = screeningService.getAllScreenings();
        }
        return ResponseEntity.status(HttpStatus.OK).body(screenings);
    }

    @GetMapping("/{screening_id}")
    public ResponseEntity<?> getScreenById(@PathVariable Long screening_id) {
        Screening screening = screeningService.getScreeningById(screening_id);
        return ResponseEntity.status(HttpStatus.OK).body(screening);
    }

    @GetMapping("/{screening_id}/seats")
    public ResponseEntity<?> getSeatsByScreeningId(@PathVariable Long screening_id) throws HouseFullException {
        List<Seat> seats = screeningService.getSeatsByScreeningId(screening_id);
        if(seats.size() == 0) {
           throw new HouseFullException();
        }
        return ResponseEntity.status(HttpStatus.OK).body(seats);
    }

    @PostMapping("/movie/add")
    public ResponseEntity<?> addMovieScreening(@RequestBody MovieScreenDao movieScreenDao) {

        return ResponseEntity.status(HttpStatus.OK).body(screeningService.addMovieScreening(movieScreenDao));
    }
}
