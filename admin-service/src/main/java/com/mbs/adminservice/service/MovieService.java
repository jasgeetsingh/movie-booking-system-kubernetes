package com.mbs.adminservice.service;



import com.mbs.adminservice.model.Movie;
import com.mbs.adminservice.model.Screening;

import java.time.LocalDate;
import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();

    Movie getMovieById(Long movieId);

    Movie addMovie(Movie newMovie);

    Movie updateMovie(Movie updatedMovie, Long movieId);

    void deleteMovie(Long movieId);


}