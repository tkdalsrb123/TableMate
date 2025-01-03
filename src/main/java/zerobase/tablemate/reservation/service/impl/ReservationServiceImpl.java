package zerobase.tablemate.reservation.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.tablemate.reservation.domain.Reservation;
import zerobase.tablemate.reservation.repository.ReservationRepository;
import zerobase.tablemate.reservation.service.ReservationService;
import zerobase.tablemate.store.domain.Store;
import zerobase.tablemate.store.repository.StoreRepository;
import zerobase.tablemate.user.domain.User;
import zerobase.tablemate.user.repository.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Override
    public Reservation reservationRegister(String userName, String storeName, LocalDateTime reservationDateTime) {
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new IllegalArgumentException("사용자 정보가 일치하지 않습니다."));
        Store store = storeRepository.findStoreByStoreName(storeName).orElseThrow(() -> new IllegalArgumentException("매장이 존재하지 않습니다."));

        return reservationRepository.save(Reservation.builder()
                .userName(userName)
                .storeName(storeName)
                .reservationDateTime(reservationDateTime)
                .build());
    }
}
