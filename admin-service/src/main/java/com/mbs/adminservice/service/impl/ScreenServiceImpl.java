package com.mbs.adminservice.service.impl;

import com.mbs.adminservice.exception.ScreenNotFoundException;
import com.mbs.adminservice.model.Screen;
import com.mbs.adminservice.model.Screening;
import com.mbs.adminservice.model.Theatre;
import com.mbs.adminservice.repository.ScreenRepository;
import com.mbs.adminservice.service.ScreenService;
import com.mbs.adminservice.service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Primary
public class ScreenServiceImpl implements ScreenService {

    @Autowired
    ScreenRepository screenRepository;

    @Autowired
    ScreeningService screeningService;

    @Override
    public List<Screen> getAllScreens() {
        return null;
    }

    @Override
    public Screen getScreenById(Long screenId) {

        return screenRepository.findById(screenId).orElseThrow(ScreenNotFoundException:: new);
    }

    @Override
    public Screen addScreen(Screen screen) {
        screen.getSeats().forEach(seat -> {
            screen.addSeat(seat);
        });
        screenRepository.save(screen);
        return screen;
    }

    @Override
    public Screen updateScreen(Screen updatedScreen, Long theatreId) {
        return null;
    }


    @Override
    public Boolean deleteScreenList(Set<Screen> screenSet) {
        // We are checking if all screen should not have any running show other
        // we can not delete any screens
        for(Screen screen : screenSet){
            if(!hasNotAnyRunningShow(screen)) return false;
        }
        // If we reach here that means we can delete screens and it screenings together
        for(Screen screen : screenSet){
            screeningService.deleteScreeningList(screen.getScreenings());
        }
        screenRepository.deleteAll(screenSet);
        return true;


    }

    @Override
    public Boolean deleteScreen(Screen screen) {
        Boolean hasNotAnyShow = hasNotAnyRunningShow(screen);
        if(hasNotAnyShow) {
            screeningService.deleteScreeningList(screen.getScreenings());
            screenRepository.delete(screen);
            return true;
        }else{
            return false;
        }

    }

    @Override
    public Boolean hasNotAnyRunningShow(Screen screen)  {
        List<Screening>  screeningList = screen.getScreenings().stream().filter(screening -> screening.getBookedSeats().size() != 0).collect(Collectors.toList());
        return screeningList.isEmpty();
    }
}
