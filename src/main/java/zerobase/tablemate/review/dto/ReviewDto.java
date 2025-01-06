package zerobase.tablemate.review.dto;


import lombok.*;
import zerobase.tablemate.review.domain.Review;

public class ReviewDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private String userName;
        private String storeName;
        private String title;
        private String content;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private String userName;
        private String storeName;
        private String title;

        public static Response of(Review review) {
            return Response.builder()
                    .userName(review.getUserName())
                    .storeName(review.getStoreName())
                    .title(review.getTitle())
                    .build();

        }
    }

}
