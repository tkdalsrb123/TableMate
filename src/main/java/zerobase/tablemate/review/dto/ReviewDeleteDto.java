package zerobase.tablemate.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDeleteDto {
    private String userName;
    private String storeName;
    private Long reviewId;
}
