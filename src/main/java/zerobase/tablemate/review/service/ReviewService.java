package zerobase.tablemate.review.service;

import zerobase.tablemate.review.domain.Review;


public interface ReviewService {

    // 리뷰 등록
    Review reviewRegister(String userName, String storeName, String title, String context);

    // 리뷰 등록 권한 확인
    void canReview(String userName, String storeName);

    // 리뷰 수정
    Review reviewModify(String userName, String storeName, String title, String content);

    // 리뷰 삭제
    Review reviewDelete(String userName, String storeName, Long reviewId);

}
