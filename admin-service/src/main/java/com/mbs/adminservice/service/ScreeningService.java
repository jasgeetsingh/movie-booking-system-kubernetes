package com.mbs.adminservice.service;




import com.mbs.adminservice.model.Screening;
import com.mbs.adminservice.model.Seat;
import com.mbs.adminservice.model.dao.MovieScreenDao;
import com.mbs.adminservice.model.dao.ScreeningDao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface ScreeningService {
    List<Screening> getAllScreenings();

    Screening getScreeningById(Long screeningId);

    Screening addMovieScreening(MovieScreenDao movieScreenDao);

    Screening updateScreening(Screening updatedScreening, Long screeningId);

    List<Seat> getSeatsByScreeningId(Long screeningId);

    List<Screening> getAllScreenings(LocalDateTime startDate, LocalDateTime endDate);

    ScreeningDao getRemainingSeatsByScreenAndMovie(Long screenId, Long movieId);

    void deleteScreeningList(Set<Screening> screeningList);
}
