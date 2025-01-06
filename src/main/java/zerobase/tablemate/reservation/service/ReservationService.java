package zerobase.tablemate.reservation.service;

import zerobase.tablemate.reservation.domain.Reservation;

import java.time.LocalDateTime;

public interface ReservationService {
    // 예약 등록
    Reservation reservationRegister(String userName, String storeName, LocalDateTime reservationDateTime);
    // 예약 권한 확인
    void canReserve(String userName, String storeName, LocalDateTime reservationDateTime);
}
