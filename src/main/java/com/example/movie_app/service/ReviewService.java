package com.example.movie_app.service;

import com.example.movie_app.domain.Movie;
import com.example.movie_app.domain.Review;
import com.example.movie_app.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> findReviewsByMovie(Movie movie) {
        return reviewRepository.findAllByMovie(movie);
    }
}
