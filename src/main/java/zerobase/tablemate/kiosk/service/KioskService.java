package zerobase.tablemate.kiosk.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.tablemate.reservation.domain.Reservation;
import zerobase.tablemate.reservation.repository.ReservationRepository;
import zerobase.tablemate.reservation.type.VisitStatus;
import zerobase.tablemate.store.domain.Store;
import zerobase.tablemate.store.repository.StoreRepository;
import zerobase.tablemate.user.domain.User;
import zerobase.tablemate.user.repository.UserRepository;
import zerobase.tablemate.user.type.UserType;

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
            throw new IllegalArgumentException("권한이 없습니다.");
        }
    }

    @Transactional
    public String visitCheck(String visitorName, String visitorPassword, String storeName) {
        User visitor = userRepository.findByUsernameAndPassword(visitorName, visitorPassword).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Reservation reservation = reservationRepository.findReservationByUserNameAndStoreName(visitor.getUsername(), storeName).orElseThrow(() -> new IllegalArgumentException("예약 정보가 없습니다."));

        reservation.setVisitStatus(VisitStatus.VISITED);

        return String.format("%s님 방문이 확인되었습니다.", visitor.getUsername());
    }

    public void isVisitor(User user) {

    }
}
