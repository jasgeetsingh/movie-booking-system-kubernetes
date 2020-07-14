package com.mbs.adminservice.repository;

import com.mbs.adminservice.model.Movie;
import com.mbs.adminservice.model.Screen;
import com.mbs.adminservice.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening, Serializable> {

       Screening findByScreenAndMovie(Screen screen, Movie movie);

}
