package zerobase.tablemate.kiosk.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    // 관리자 등록
    public Store registerManager(String adminName, String adminPassword) {
        adminPassword = passwordEncoder.encode(adminPassword);
        User admin = userRepository.findByUsernameAndPassword(adminName, adminPassword).orElseThrow(() -> new ErrorResponseException(USER_NOT_FOUND));
        isAdmin(admin);
        Store store = storeRepository.findByUserName(admin.getUsername()).orElseThrow(() -> new ErrorResponseException(STORE_NOT_FOUND));;
        return store;
    }

    // 관리자 권한 확인
    public void isAdmin(User user) {
        if (user.getUserType() != UserType.MANAGER && !user.isPartnerMember()) {
            throw new ErrorResponseException(STORE_ACCESS_DENIED);
        }
    }

    // 방문자 체크
    @Transactional
    public String visitCheck(String visitorName, String visitorPassword, String storeName) {
        visitorPassword = passwordEncoder.encode(visitorPassword);
        User visitor = userRepository.findByUsernameAndPassword(visitorName, visitorPassword).orElseThrow(() -> new ErrorResponseException(USER_NOT_FOUND));

        Reservation reservation = reservationRepository.findReservationByUserNameAndStoreName(visitor.getUsername(), storeName).orElseThrow(() -> new ErrorResponseException(RESERVATION_NOT_FOUND));

        reservation.setVisitStatus(VisitStatus.VISITED);

        return String.format("%s님 방문이 확인되었습니다.", visitor.getUsername());
    }

}
