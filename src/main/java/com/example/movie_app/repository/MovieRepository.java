package com.example.movie_app.repository;

import com.example.movie_app.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT m FROM Movie m " +
            "WHERE (:title IS NULL OR m.title = :title) " +
            "AND (:genre IS NULL OR m.genre = :genre) " +
            "AND (:genres IS NULL OR m.genre IN :genres) " +
            "AND (:year IS NULL OR m.year = :year) " +
            "AND (:yearFrom IS NULL OR m.year >= :yearFrom) " +
            "AND (:yearTo IS NULL OR m.year <= :yearTo)")
    Page<Movie> findMoviesByFilters(
            @Param("title") String title,
            @Param("genre") String genre,
            @Param("genres") List<String> genres,
            @Param("year") Integer year,
            @Param("yearFrom") Integer yearFrom,
            @Param("yearTo") Integer yearTo,
            Pageable pageable
    );
}
