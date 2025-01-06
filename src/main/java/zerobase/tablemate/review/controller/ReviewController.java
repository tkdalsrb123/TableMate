package zerobase.tablemate.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zerobase.tablemate.review.domain.Review;
import zerobase.tablemate.review.dto.ReviewDto;
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


}
