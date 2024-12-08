package com.fargaislam.firstJobApp.review.impl;

import com.fargaislam.firstJobApp.company.Company;
import com.fargaislam.firstJobApp.company.CompanyService;
import com.fargaislam.firstJobApp.review.Review;
import com.fargaislam.firstJobApp.review.ReviewRepository;
import com.fargaislam.firstJobApp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository,CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService=companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Review review, Long companyId) {
        Company company =companyService.getCompanyById(companyId);
        if(company!=null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        else{
            return false;
        }
    }
    @Override
    public Review getReview(Long companyId,Long reviewId) {
         List<Review> reviews=reviewRepository.findByCompanyId(companyId);

            return reviews.stream().filter(review->review.getId().equals(reviewId))
                    .findFirst()
                    .orElse(null);






    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if(companyService.getCompanyById(companyId)!=null){
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {

        if(companyService.getCompanyById(companyId)!=null
        &&reviewRepository.existsById(reviewId)){
            Review review=reviewRepository.findById(reviewId).orElse(null);
            Company company=review.getCompany();
            company.getReviews().remove(review);

            companyService.updateCompany(company,companyId);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        else{
            return false;
        }
    }

}
