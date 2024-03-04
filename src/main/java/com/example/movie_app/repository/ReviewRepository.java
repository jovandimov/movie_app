package com.example.movie_app.repository;

import com.example.movie_app.domain.Movie;
import com.example.movie_app.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByMovieID(Long id);

    Page<Review> findAllByMovieID(Long id, Pageable pageable);

    Page<Review> findAllByMovieIDAndReviewTextContaining(Long id, String reviewText, Pageable pageable);

    @Query("SELECT r FROM Review r " +
            "WHERE (:movieId IS NULL OR r.movieID = :movieId) " +
            "AND (:reviewText IS NULL OR r.reviewText LIKE %:reviewText%) " +
            "ORDER BY r.Id ASC")
    Page<Review> findMoviesByFilters(Long movieId, String reviewText, Pageable pageable);

}

