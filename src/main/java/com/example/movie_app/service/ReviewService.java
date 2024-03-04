package com.example.movie_app.service;

import com.example.movie_app.domain.Movie;
import com.example.movie_app.domain.Review;
import com.example.movie_app.dto.ReviewDTO;
import com.example.movie_app.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final MovieService movieService;
    private final ReviewRepository reviewRepository;

    public ReviewService(MovieService movieService, ReviewRepository reviewRepository) {
        this.movieService = movieService;
        this.reviewRepository = reviewRepository;
    }

    public List<Review> findReviewsByMovie(Long movie) {
        return reviewRepository.findAllByMovieID(movie);
    }

    public Page<Review> findReviewsByMovie(Long movieId, String reviewText, Pageable pageable) {
        if (reviewText != null && !reviewText.isEmpty()) {
            return reviewRepository.findAllByMovieIDAndReviewTextContaining(movieId, reviewText, pageable);
        } else {
            return reviewRepository.findAllByMovieID(movieId, pageable);
        }
    }

    public Page<ReviewDTO> findReviewsByFilters(Long movieId, String reviewText, Pageable pageable) {
        Page<Review> reviews = reviewRepository.findMoviesByFilters(movieId, reviewText, pageable);
        return reviews.map(this::mapToReviewDTO);
    }

    public Review addReview(Long movieId,String reviewText){
        return reviewRepository.save(new Review(movieId,reviewText));
    }

    public ReviewDTO mapToReviewDTO(Review review) {
        Movie movie = movieService.findMovieById(review.getMovieID());
        double averageRating = movieService.calcAverageRating(movie.getRatings());
        return new ReviewDTO(movie.getId(), movie.getTitle(), movie.getDescription(), review, averageRating);
    }
}
