package zerobase.tablemate.aop.exception;


import lombok.extern.slf4j.Slf4j;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ErrorResponseException.class)
    public ErrorResponseDto handle(ErrorResponseException ex) {
        log.error("Error occurred: {}", ex.getMessage());
        return new ErrorResponseDto(ex.getError(), ex.getMessage(), ex.getTimestamp());
    }
}
