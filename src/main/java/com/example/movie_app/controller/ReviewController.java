package com.example.movie_app.controller;

import com.example.movie_app.domain.Movie;
import com.example.movie_app.domain.Review;
import com.example.movie_app.dto.ReviewDTO;
import com.example.movie_app.service.MovieService;
import com.example.movie_app.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<Page<ReviewDTO>> listReviews(@RequestParam(required = false) Long movieID,
                                                       @RequestParam(required = false) String reviewText,
                                                       @PageableDefault(size = 10) Pageable pageable) {
        Page<ReviewDTO> review = reviewService.findReviewsByFilters(movieID, reviewText, pageable);
        return ResponseEntity.ok(review);
    }
}
