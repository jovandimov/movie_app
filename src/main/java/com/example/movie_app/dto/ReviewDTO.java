package com.example.movie_app.dto;

import com.example.movie_app.domain.Movie;
import com.example.movie_app.domain.Rating;
import com.example.movie_app.domain.Review;

import java.util.List;

public class ReviewDTO {

    public ReviewDTO(Long movieId, String name, String description, Review review, Double averageRating) {
        this.movieId = movieId;
        this.name = name;
        this.description = description;
        this.review = review;
        this.averageRating = averageRating;
    }

    private Long movieId;

    private String name;

    private String description;

    private Review review;

    private Double averageRating;

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
