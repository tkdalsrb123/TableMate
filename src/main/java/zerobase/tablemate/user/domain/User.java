package zerobase.tablemate.user.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import zerobase.tablemate.user.type.UserType;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints =
    @UniqueConstraint(columnNames = {"username", "password", "email", "phone"})
)
@Builder
@EntityListeners(AuditingEntityListener.class)
// 회원 객체
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String username;
    private String password;
    @Column
    private String email;
    @Column
    private String phone;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private boolean partnerMember;

    @CreatedDate
    private LocalDateTime registeredAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

}
