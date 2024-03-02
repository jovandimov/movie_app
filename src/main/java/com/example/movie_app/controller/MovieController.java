package com.example.movie_app.controller;

import com.example.movie_app.domain.Movie;
import com.example.movie_app.domain.Rating;
import com.example.movie_app.domain.Review;
import com.example.movie_app.service.MovieService;
import com.example.movie_app.service.RatingService;
import com.example.movie_app.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/allMovies")
    public List<Movie> allMoviesList() {
        return movieService.findAllMovies();
    }

    @GetMapping
    public ResponseEntity<Page<Movie>> listMovies(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) List<String> genres,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer yearFrom,
            @RequestParam(required = false) Integer yearTo,
            @PageableDefault(size = 20, sort = {"title"}, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<Movie> movies = movieService.findMoviesByFilters(title, genre, genres, year, yearFrom, yearTo, pageable);
        return ResponseEntity.ok(movies);
    }

    @PostMapping("/add")
    public void addMovie(@RequestBody Movie movie) {
        Movie newMovie = movieService.addMovie(movie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findMovieById(@RequestParam(required = true) Long id) {
        Movie movie = movieService.findMovieById(id);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/ratings")
    public ResponseEntity<List<Rating>> getRatingsForMovie(@PathVariable Long id) {
        Movie movie = movieService.findMovieById(id);
        List<Rating> ratings = ratingService.findRatingsByMovie(movie);
        if (ratings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<Review>> getReviewsForMovie(@PathVariable Long id) {
        Movie movie = movieService.findMovieById(id);
        List<Review> reviews = reviewService.findReviewsByMovie(movie);
        if (reviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reviews);
    }
}
