package zerobase.tablemate.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.tablemate.review.domain.Review;

@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDeleteDto {
    private String userName;
    private String storeName;
    private Long reviewId;

    public static ReviewDeleteDto from(Review review) {
        return ReviewDeleteDto.builder()
                .userName(review.getUserName())
                .storeName(review.getStoreName())
                .reviewId(review.getId())
                .build();
    }
}
