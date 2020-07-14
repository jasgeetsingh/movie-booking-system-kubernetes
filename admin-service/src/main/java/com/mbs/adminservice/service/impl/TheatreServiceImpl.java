package com.mbs.adminservice.service.impl;


import com.mbs.adminservice.model.Theatre;
import com.mbs.adminservice.repository.TheatreRepository;
import com.mbs.adminservice.service.ScreenService;
import com.mbs.adminservice.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class TheatreServiceImpl implements TheatreService {

     @Autowired
     TheatreRepository theatreRepository;

     @Autowired
     ScreenService screenService;

    @Override
    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    @Override
    public Theatre getTheatreById(Long theatreId) {
        return null;
    }

    @Override
    public Theatre addTheatre(Theatre newTheatre) {

        newTheatre.getScreens().forEach(screen -> {
            screen.getSeats().forEach(
                    seat -> screen.addSeat(seat)
            );
            screen.getScreenings().forEach(screening ->
                screen.addScreening(screening)
            );
            newTheatre.addScreens(screen);
        });
        return theatreRepository.save(newTheatre);
    }

    @Override
    public Theatre updateTheatre(Theatre updatedTheatre, Long theatreId) {
        return null;
    }

    @Override
    public void deleteTheatreById(Long theatreId) {
          Theatre theatre = getTheatreById(theatreId) ;
          Boolean canDeleteTheatre = screenService.deleteScreenList(theatre.getScreens());
          if(canDeleteTheatre) {theatreRepository.delete(theatre); }
    }
}
