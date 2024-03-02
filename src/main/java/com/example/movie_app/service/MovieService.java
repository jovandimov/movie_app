package com.example.movie_app.service;

import com.example.movie_app.domain.Movie;
import com.example.movie_app.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    public Page<Movie> findMoviesByFilters(String title, String genre, List<String> genres, Integer year, Integer yearFrom, Integer yearTo, Pageable pageable) {
        return movieRepository.findMoviesByFilters(title, genre, genres, year, yearFrom, yearTo, pageable);
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie findMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }
}
