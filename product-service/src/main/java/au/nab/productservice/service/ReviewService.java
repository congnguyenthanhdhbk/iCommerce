package au.nab.productservice.service;

import au.nab.productservice.dtos.ReviewDto;
import au.nab.productservice.entities.Review;

public interface ReviewService {
    Review addReview(ReviewDto review);
}
