package zerobase.tablemate.aop.exception;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponseException extends RuntimeException {
    private ErrorCode error;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponseException(@NotNull ErrorCode errorCode) {
        this.error = errorCode;
        this.message = errorCode.getMessage();
        this.timestamp = LocalDateTime.now();
    }
}
