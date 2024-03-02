package com.example.movie_app.repository;

import com.example.movie_app.domain.Movie;
import com.example.movie_app.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findAllByMovie(Movie id);
}
