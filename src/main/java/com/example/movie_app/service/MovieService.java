package com.example.movie_app.service;

import com.example.movie_app.domain.Movie;
import com.example.movie_app.domain.Rating;
import com.example.movie_app.domain.Review;
import com.example.movie_app.dto.MovieDetailsDTO;
import com.example.movie_app.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    public Page<MovieDetailsDTO> findMoviesByFilters(String title, String genre, String description, List<String> genres, Integer year, Integer yearFrom, Integer yearTo, Pageable pageable) {
        Page<Movie> movies = movieRepository.findMoviesByFilters(title, genre, description, genres, year, yearFrom, yearTo, pageable);
        return movies.map(this::mapToMovieDetailsDTOWithAverageRating);
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie findMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    public List<Rating> findRatingsByMovieId(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        return movie != null ? movie.getRatings() : null;
    }

    public List<Review> findReviewsByMovieId(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        return movie != null ? movie.getReviews() : null;
    }

    private MovieDetailsDTO mapToMovieDetailsDTOWithAverageRating(Movie movie) {
        List<Rating> ratings = movie.getRatings();
        List<Review> reviews = movie.getReviews();
        double averageRating = calcAverageRating(ratings);
        return new MovieDetailsDTO(movie, averageRating);
    }

    public Double calcAverageRating(List<Rating> ratings) {
        return ratings.stream().mapToDouble(Rating::getRating).average().orElse(0.0);
    }
}
