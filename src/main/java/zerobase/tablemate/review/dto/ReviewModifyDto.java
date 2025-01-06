package zerobase.tablemate.review.dto;


import lombok.*;
import zerobase.tablemate.review.domain.Review;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
// 리뷰 수정 DTO
public class ReviewModifyDto {
    private String userName;
    private String storeName;
    private String title;
    private String content;

    public static ReviewModifyDto from(Review review) {
        return ReviewModifyDto.builder()
                .userName(review.getUserName())
                .storeName(review.getStoreName())
                .title(review.getTitle())
                .content(review.getContent())
                .build();
    }
}
