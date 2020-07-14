package com.mbs.adminservice.controller;


import com.mbs.adminservice.model.Screen;
import com.mbs.adminservice.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/mbs")
public class ScreenController {
    @Autowired
    ScreenService screenService;

    @GetMapping("/screens")
    public List<Screen> getAllTheatres() {
        return screenService.getAllScreens();
    }
}
