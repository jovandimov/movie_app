package com.example.movie_app.repository;

import com.example.movie_app.domain.Movie;
import com.example.movie_app.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByMovie(Movie id);
}
