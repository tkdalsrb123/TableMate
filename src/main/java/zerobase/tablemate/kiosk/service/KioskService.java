package zerobase.tablemate.kiosk.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.tablemate.aop.exception.ErrorResponseException;
import zerobase.tablemate.reservation.domain.Reservation;
import zerobase.tablemate.reservation.repository.ReservationRepository;
import zerobase.tablemate.reservation.type.VisitStatus;
import zerobase.tablemate.store.domain.Store;
import zerobase.tablemate.store.repository.StoreRepository;
import zerobase.tablemate.user.domain.User;
import zerobase.tablemate.user.repository.UserRepository;
import zerobase.tablemate.user.type.UserType;

import static zerobase.tablemate.aop.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class KioskService {
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final ReservationRepository reservationRepository;

    public Store registerManager(String adminName, String adminPassword) {
        User admin = userRepository.findByUsernameAndPassword(adminName, adminPassword).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        isAdmin(admin);
        Store store = storeRepository.findByUserName(admin.getUsername()).orElseThrow(() -> new IllegalArgumentException("등록된 매장이 없습니다."));;
        return store;
    }

    public void isAdmin(User user) {
        if (user.getUserType() != UserType.MANAGER && !user.isPartnerMember()) {
            throw new ErrorResponseException(STORE_ACCESS_DENIED);
        }
    }

    @Transactional
    public String visitCheck(String visitorName, String visitorPassword, String storeName) {
        User visitor = userRepository.findByUsernameAndPassword(visitorName, visitorPassword).orElseThrow(() -> new ErrorResponseException(USER_NOT_FOUND));

        Reservation reservation = reservationRepository.findReservationByUserNameAndStoreName(visitor.getUsername(), storeName).orElseThrow(() -> new ErrorResponseException(RESERVATION_NOT_FOUND));

        reservation.setVisitStatus(VisitStatus.VISITED);

        return String.format("%s님 방문이 확인되었습니다.", visitor.getUsername());
    }

}
