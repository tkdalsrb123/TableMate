package zerobase.tablemate.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zerobase.tablemate.review.dto.ReviewDeleteDto;
import zerobase.tablemate.review.dto.ReviewDto;
import zerobase.tablemate.review.dto.ReviewModifyDto;
import zerobase.tablemate.review.service.ReviewService;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 등록
    @PostMapping("/review/register")
    public ReviewDto.Response reviewRegister(@RequestBody ReviewDto.Request request) {
        return ReviewDto.Response.of(reviewService.reviewRegister(
                request.getUserName(),
                request.getStoreName(),
                request.getTitle(),
                request.getContent()));
    }

    // 리뷰 수정
    @PutMapping("/review/modify")
    public ReviewModifyDto reviewModify(@RequestBody ReviewModifyDto request) {
        return ReviewModifyDto.from(reviewService.reviewModify(
                request.getUserName(),
                request.getStoreName(),
                request.getTitle(),
                request.getContent()));
    }

    // 리뷰 삭제
    @DeleteMapping("/review/delete")
    public ReviewDeleteDto reviewDelete(@RequestBody ReviewDeleteDto request) {
        return ReviewDeleteDto.from(reviewService.reviewDelete(
                request.getUserName(),
                request.getStoreName(),
                request.getReviewId()));
    }
}
