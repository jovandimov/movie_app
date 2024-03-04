package com.example.movie_app.service;

import com.example.movie_app.domain.Movie;
import com.example.movie_app.domain.Rating;
import com.example.movie_app.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<Rating> findRatingsByMovie(Long movieId) {
        return ratingRepository.findAllByMovieID(movieId);
    }

    public Rating addRating(Long movieId, Integer rating) {
        return ratingRepository.save(new Rating(movieId, rating));
    }
}
