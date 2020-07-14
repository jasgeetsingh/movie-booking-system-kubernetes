package com.mbs.adminservice.service.impl;


import com.mbs.adminservice.exception.HouseFullException;
import com.mbs.adminservice.exception.ScreeningNotFoundException;
import com.mbs.adminservice.model.Movie;
import com.mbs.adminservice.model.Screen;
import com.mbs.adminservice.model.Screening;
import com.mbs.adminservice.model.Seat;
import com.mbs.adminservice.model.dao.MovieScreenDao;
import com.mbs.adminservice.model.dao.ScreeningDao;
import com.mbs.adminservice.repository.ScreeningRepository;
import com.mbs.adminservice.service.MovieService;
import com.mbs.adminservice.service.ScreenService;
import com.mbs.adminservice.service.ScreeningService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Primary
public class ScreeningServiceImpl implements ScreeningService {

    @Autowired
    ScreeningRepository screeningRepository;

    @Autowired
    MovieService movieService;

    @Autowired
    ScreenService screenService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ScreeningServiceImpl.class);

    @Override
    public List<Screening> getAllScreenings() {
        return screeningRepository.findAll();
    }

    @Override
    public List<Screening> getAllScreenings(LocalDateTime startDate, LocalDateTime endDate) {
        return screeningRepository.findAll().stream().filter(screening -> screening.getDate().isAfter(startDate) && screening.getDate().isBefore(endDate)).collect(Collectors.toList());
    }

    @Override
    public Screening getScreeningById(Long screeningId) {
        return screeningRepository.findById(screeningId).orElseThrow(ScreeningNotFoundException:: new);
    }

    @Override
    public Screening addMovieScreening(MovieScreenDao movieScreenDao) {
        Screening screening = getScreeningById(movieScreenDao.getScreeningId());
        screening.setMovie(movieService.getMovieById(movieScreenDao.getMovieId()));
        return screeningRepository.save(screening);
    }

    @Override
    public Screening updateScreening(Screening updatedScreening, Long screening_id) {
        return null;
    }

    @Override
    public List<Seat> getSeatsByScreeningId(Long screeningId) {
        Screening screening = getScreeningById(screeningId);
        if (screening.getFull()) {
            return new ArrayList();
        }
        return new ArrayList(screening.getScreen().getSeats());
    }


    @Override
    public ScreeningDao getRemainingSeatsByScreenAndMovie(Long screenId, Long movieId)  {
        Movie movie = movieService.getMovieById(movieId);
        Screen screen = screenService.getScreenById(screenId);
        Screening screening = screeningRepository.findByScreenAndMovie(screen, movie);
        if (screening.getFull()) {
           return null;
        }
        ScreeningDao screeningDao = new ScreeningDao();
        List<Seat> bookedSeats = screening.getBookedSeats().stream().map(seatBooked -> seatBooked.getSeat()).collect(Collectors.toList());
        List<Seat> remainingSeats = new ArrayList(screening.getScreen().getSeats());
        remainingSeats.removeAll(bookedSeats);
        screeningDao.setRemainingSeats(remainingSeats);
        screeningDao.setScreening(screening);
        return screeningDao;
    }

    @Override
    public void deleteScreeningList(Set<Screening> screeningList) {
         screeningRepository.deleteAll(screeningList);
    }


}
