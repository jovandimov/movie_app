package com.example.movie_app.controller;

import com.example.movie_app.domain.Movie;
import com.example.movie_app.domain.Rating;
import com.example.movie_app.domain.Review;
import com.example.movie_app.dto.MovieDetailsDTO;
import com.example.movie_app.request.RatingRequest;
import com.example.movie_app.request.ReviewRequest;
import com.example.movie_app.service.MovieService;
import com.example.movie_app.service.RatingService;
import com.example.movie_app.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "*")
public class MovieController {

    private final MovieService movieService;

    private final RatingService ratingService;

    private final ReviewService reviewService;

    public MovieController(MovieService movieService, RatingService ratingService, ReviewService reviewService) {
        this.movieService = movieService;
        this.ratingService = ratingService;
        this.reviewService = reviewService;
    }

    @GetMapping("/allMovies")
    public List<Movie> allMoviesList() {
        return movieService.findAllMovies();
    }

    @GetMapping
    public ResponseEntity<Page<MovieDetailsDTO>> listMovies(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) List<String> genres,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer yearFrom,
            @RequestParam(required = false) Integer yearTo,
            @PageableDefault(size = 20, sort = {"title"}, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<MovieDetailsDTO> movies = movieService.findMoviesByFilters(title, genre, description, genres, year, yearFrom, yearTo, pageable);

        return ResponseEntity.ok(movies);
    }

    @PostMapping("/add")
    public void addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDetailsDTO> findMovieById(@PathVariable Long id) {
        Movie movie = movieService.findMovieById(id);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }

        List<Rating> ratings = ratingService.findRatingsByMovie(id);
        List<Review> reviews = reviewService.findReviewsByMovie(id);
        double averageRating = movieService.calcAverageRating(ratings);

        MovieDetailsDTO movieDetailsDTO = new MovieDetailsDTO(movie, ratings, reviews, averageRating);

        return ResponseEntity.ok(movieDetailsDTO);
    }

    @PostMapping("/{id}/rate")
    public ResponseEntity<String> addRatingToMovie(@PathVariable Long id, @RequestBody RatingRequest request) {
        if (request.getRating() < 1 || request.getRating() > 10) {
            return ResponseEntity.badRequest().body("Rating should be between 1 and 10.");
        }
        Rating rating = ratingService.addRating(id, request.getRating());

        if (rating != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Rating added successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add rating.");
        }
    }

    @PostMapping("/{id}/review")
    public ResponseEntity<String> addReviewToMovie(@PathVariable Long id, @RequestBody ReviewRequest request) {
        if (request.getReviewText() == null || request.getReviewText().isEmpty()) {
            return ResponseEntity.badRequest().body("Review text cannot be empty.");
        }
        Review review = reviewService.addReview(id, request.getReviewText());

        if (review != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Review added successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add review.");
        }
    }
}
