package au.nab.productservice.service.impl;

import au.nab.productservice.dtos.ReviewDto;
import au.nab.productservice.entities.Review;
import au.nab.productservice.repository.ReviewRepository;
import au.nab.productservice.service.ReviewService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class ReviewImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review addReview(ReviewDto review) {
        final Review reviewEntity = Review.
                builder()
                .reviewBy(review.getReviewBy())
                .comment(review.getComment())
                .images(review.getImages())
                .start(review.getStar())
                .build();
        return reviewRepository.insert(reviewEntity);
    }
}
