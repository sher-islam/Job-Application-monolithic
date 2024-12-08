package com.fargaislam.firstJobApp.review;

import com.fargaislam.firstJobApp.company.Company;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){

        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }
    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@RequestBody Review review,
                                            @PathVariable Long companyId){
        boolean reviewAdded=reviewService.addReview(review,companyId);
        if(reviewAdded){
            return new ResponseEntity<>("Review added successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Company not found",HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getreview(@PathVariable Long companyId,
                                            @PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getReview(
                companyId,reviewId),HttpStatus.OK);
    }
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,
                               @PathVariable Long reviewId,
                               @RequestBody Review updatedReview){
        boolean isReviewUpdated= reviewService.updateReview(companyId,reviewId,updatedReview);
        if(isReviewUpdated){
            return new ResponseEntity<>("Review updated successfully"
                    ,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Review not updated"
                    ,HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,
                                                @PathVariable Long reviewId){
        boolean isDeleted= reviewService.deleteReview(companyId,reviewId);
        if(isDeleted){
            return new ResponseEntity<>("Review deleted successfully"
                    ,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Review not deleted"
                    ,HttpStatus.NOT_FOUND);
        }


    }


}
