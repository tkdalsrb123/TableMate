package zerobase.tablemate.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zerobase.tablemate.review.domain.Review;
import zerobase.tablemate.review.dto.ReviewDeleteDto;
import zerobase.tablemate.review.dto.ReviewDto;
import zerobase.tablemate.review.dto.ReviewModifyDto;
import zerobase.tablemate.review.service.ReviewService;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/review/register")
    public ReviewDto.Response reviewRegister(@RequestBody ReviewDto.Request request) {
        return ReviewDto.Response.of(reviewService.reviewRegister(
                request.getUserName(),
                request.getStoreName(),
                request.getTitle(),
                request.getContent()));
    }

    @PutMapping("/review/modify")
    public String reviewModify(@RequestBody ReviewModifyDto request) {
        return reviewService.reviewModify(request.getUserName(), request.getStoreName(), request.getTitle(), request.getContent());
    }

    @DeleteMapping("/review/delete")
    public String reviewDelete(@RequestBody ReviewDeleteDto request) {
        return reviewService.reviewDelete(request.getUserName(), request.getStoreName(), request.getReviewId());
    }
}
