package zerobase.tablemate.aop.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 사용자 관련 에러
    USER_ALREADY_EXIST("이미 사용자가 존재합니다."),
    USER_NOT_FOUND("사용자가 존재하지 않습니다."),

    // 매장 관련 에러
    STORE_NOT_FOUND("매장이 존재하지 않습니다."),
    STORE_ALREADY_EXISTS("이미 등록된 매장입니다."),
    STORE_ACCESS_DENIED("해당 매장에 대한 접근 권한이 없습니다."),

    // 예약 관련 에러
    RESERVATION_NOT_AVAILABLE("해당 시간은 예약이 불가능합니다."),
    RESERVATION_ALREADY_EXISTS("이미 예약이 존재합니다."),
    RESERVATION_NOT_FOUND("예약 정보를 찾을 수 없습니다."),

    // 리뷰 관련 에러
    REVIEW_NOT_FOUND("리뷰가 존재하지 않습니다."),
    REVIEW_ACCESS_DENIED("해당 리뷰에 대한 접근 권한이 없습니다."),

    ;

    final private String message;
}
