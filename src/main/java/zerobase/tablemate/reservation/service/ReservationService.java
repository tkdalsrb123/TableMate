package zerobase.tablemate.reservation.service;

import zerobase.tablemate.reservation.domain.Reservation;

import java.time.LocalDateTime;

public interface ReservationService {
    Reservation reservationRegister(String userName, String storeName, LocalDateTime reservationDateTime);
}
