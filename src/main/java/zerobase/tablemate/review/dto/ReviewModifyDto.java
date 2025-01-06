package zerobase.tablemate.review.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewModifyDto {
    private String userName;
    private String storeName;
    private String title;
    private String content;
}
