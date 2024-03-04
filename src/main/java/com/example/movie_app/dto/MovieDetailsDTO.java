package com.example.movie_app.dto;

import com.example.movie_app.domain.Movie;
import com.example.movie_app.domain.Rating;
import com.example.movie_app.domain.Review;

import java.util.List;
import java.util.OptionalDouble;

public class MovieDetailsDTO {
    private Movie movie;
    private List<Rating> ratings;
    private List<Review> reviews;
    private Double averageRating;

    public MovieDetailsDTO(Movie movie, List<Rating> ratings, List<Review> reviews, double averageRating) {
        this.movie = movie;
        this.ratings = ratings;
        this.reviews = reviews;
        this.averageRating = averageRating;
    }

    public MovieDetailsDTO(Movie movie, double averageRating) {
        //Used to show only movie and average rating, i dont need the actual ratings and reviews here.
        this.movie = movie;
        this.averageRating = averageRating;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
