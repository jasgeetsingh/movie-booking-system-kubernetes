package com.mbs.adminservice.service.impl;


import com.mbs.adminservice.exception.HouseFullException;
import com.mbs.adminservice.exception.SeatsNotAvailableException;
import com.mbs.adminservice.model.*;
import com.mbs.adminservice.model.dao.BookingDao;
import com.mbs.adminservice.model.dao.ScreeningDao;
import com.mbs.adminservice.repository.BookingRepository;
import com.mbs.adminservice.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Primary
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ScreeningService screeningService;

    @Autowired
    UserService userService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Long booking_id) {
        return null;
    }

    @Override
    public BookingDao newBooking(BookingDao newBooking) throws HouseFullException {
       ScreeningDao screeningDao = screeningService.getRemainingSeatsByScreenAndMovie(newBooking.getScreenId(), newBooking.getMovieId());
       Optional.ofNullable(screeningDao).orElseThrow(HouseFullException::new);
       Integer seatsRequired = newBooking.getSeatRequired();
       Integer seatAvailable = screeningDao.getRemainingSeats().size();

       if(seatsRequired > seatAvailable){
           throw new SeatsNotAvailableException(" Only Seat Left --> "+seatAvailable);
       }
       User user = userService.getUserById(newBooking.getUserId());
       Booking booking = new Booking();
       booking.setScreening(screeningDao.getScreening());
       booking.setBookingTime(LocalTime.now());
       booking.setUser(user);
       List<Seat> seatOccupying = screeningDao.getRemainingSeats().subList(0,seatsRequired-1);
        Set<SeatBooked> seatBookedSet = new HashSet<>();
       seatOccupying.forEach(seat -> {
           SeatBooked seatBooked = new SeatBooked();
           seatBooked.setBooking(booking);
           seatBooked.setScreening(screeningDao.getScreening());
           seatBooked.setSeat(seat);
           seatBookedSet.add(seatBooked);
       });
       booking.getScreening().setBookedSeats(seatBookedSet);
       booking.setBookedSeats(seatBookedSet);
       Set<Booking> bookings = new HashSet<>();
        bookings.add(booking);
       user.setBookings(bookings);
       bookingRepository.save(booking);
       newBooking.setMessage("Your Booking is successfully Completed");
        return newBooking;
    }

    @Override
    public Booking updateBooking(Booking updatedBooking, Long booking_id) {
        return null;
    }

    @Override
    public void deleteBookingById(Long booking_id) {

    }
}

