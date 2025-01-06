package zerobase.tablemate.store.domain;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="store", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_name", "store_name", "store_address", "store_phone"})
})
@EntityListeners(AuditingEntityListener.class)
// 매장 객체
public class Store {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String userName;
    @Column
    private String storeName;
    @Column
    private String storeAddress;
    @Column
    private String storePhone;

    @CreatedDate
    private LocalDateTime registeredAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
