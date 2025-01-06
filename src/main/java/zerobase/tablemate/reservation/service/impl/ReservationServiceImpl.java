package zerobase.tablemate.reservation.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.tablemate.aop.exception.ErrorCode;
import zerobase.tablemate.aop.exception.ErrorResponseException;
import zerobase.tablemate.reservation.domain.Reservation;
import zerobase.tablemate.reservation.repository.ReservationRepository;
import zerobase.tablemate.reservation.service.ReservationService;
import zerobase.tablemate.reservation.type.VisitStatus;
import zerobase.tablemate.store.domain.Store;
import zerobase.tablemate.store.repository.StoreRepository;
import zerobase.tablemate.user.domain.User;
import zerobase.tablemate.user.repository.UserRepository;

import java.time.LocalDateTime;

import static zerobase.tablemate.aop.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Override
    public Reservation reservationRegister(String userName, String storeName, LocalDateTime reservationDateTime) {
        canReserve(userName, storeName, reservationDateTime);
        return reservationRepository.save(Reservation.builder()
                .userName(userName)
                .storeName(storeName)
                .reservationDateTime(reservationDateTime)
                .visitStatus(VisitStatus.NOT_VISITED)
                .build());
    }

    @Override
    public void canReserve(String userName, String storeName, LocalDateTime reservationDateTime) {
        if (!userRepository.existsByUsername(userName)) {
            throw new ErrorResponseException(USER_NOT_FOUND);
        }
        if (!storeRepository.existsByStoreName(storeName)) {
            throw new ErrorResponseException(STORE_NOT_FOUND);
        }

        if (reservationRepository.existsReservationByReservationDateTime(reservationDateTime)) {
            throw new ErrorResponseException(RESERVATION_NOT_AVAILABLE);
        }

    }
}
