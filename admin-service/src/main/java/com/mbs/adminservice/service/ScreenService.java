package com.mbs.adminservice.service;

import com.mbs.adminservice.model.Screen;

import java.util.List;
import java.util.Set;

public interface ScreenService {
    List<Screen> getAllScreens();

    Screen getScreenById(Long screenId);

    Screen addScreen(Screen newScreen);

    Screen updateScreen(Screen updatedScreen, Long screenId);

    Boolean deleteScreen(Screen screen);

    Boolean hasNotAnyRunningShow(Screen screen);

    Boolean deleteScreenList(Set<Screen> screenSet);
}
