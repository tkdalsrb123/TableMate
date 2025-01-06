package zerobase.tablemate.review.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="review")
@EntityListeners(AuditingEntityListener.class)
// 리뷰 객체
public class Review {
    @Id
    @GeneratedValue
    private long id;
    private String userName;
    private String storeName;
    private String title;
    private String content;

    @CreatedDate
    private LocalDateTime wroteDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;

}
