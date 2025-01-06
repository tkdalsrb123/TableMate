package zerobase.tablemate.review.service;

import zerobase.tablemate.review.domain.Review;


public interface ReviewService {

    Review reviewRegister(String userName, String storeName, String title, String context);

    void canReview(String userName, String storeName);

    String reviewModify(String userName, String storeName, String title, String content);

    String reviewDelete(String userName, String storeName, Long reviewId);

}
