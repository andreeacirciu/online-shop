package com.example.onlineshop.service;

import com.example.onlineshop.domain.Review;
import com.example.onlineshop.persistence.ReviewRepository;
import com.example.onlineshop.transfer.review.ReviewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Page<ReviewResponse> getReviews(long productId, Pageable pageable){
        Page<Review> page = reviewRepository.findByProductId(productId, pageable);

        List<ReviewResponse> reviewDtos = new ArrayList<>();

        for(Review review : page.getContent()){
            ReviewResponse reviewResponse = mapReviewResponse(review);

            reviewDtos.add(reviewResponse);

        }
        return new PageImpl<>(reviewDtos, pageable, page.getTotalElements());
    }

    private ReviewResponse mapReviewResponse(Review review){
        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setId(review.getId());
        reviewResponse.setContent(review.getContent());

        return reviewResponse;
    }
}
