package zerobase.tablemate.reservation.dto;

import lombok.*;
import zerobase.tablemate.reservation.domain.Reservation;

import java.time.LocalDateTime;

// 예약 등록 DTO
public class ReservationRegisterDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private String userName;
        private String storeName;
        private String reservationDateTime;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private String userName;
        private String storeName;
        private LocalDateTime reservationDateTime;
        private LocalDateTime registerDateTime;

        public static ReservationRegisterDto.Response of(Reservation reservation) {
            return Response.builder()
                    .userName(reservation.getUserName())
                    .storeName(reservation.getStoreName())
                    .reservationDateTime(reservation.getReservationDateTime())
                    .registerDateTime(LocalDateTime.now())
                    .build();
        }
    }
}
