package zerobase.tablemate.review.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.tablemate.reservation.domain.Reservation;
import zerobase.tablemate.reservation.repository.ReservationRepository;
import zerobase.tablemate.reservation.type.VisitStatus;
import zerobase.tablemate.review.domain.Review;
import zerobase.tablemate.review.repository.ReviewRepository;
import zerobase.tablemate.review.service.ReviewService;
import zerobase.tablemate.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public Review reviewRegister(String userName, String storeName, String title, String content) {
        canReview(userName, storeName);
        return reviewRepository.save(Review.builder()
                .userName(userName)
                .storeName(storeName)
                .title(title)
                .content(content)
                .build());
    }

    @Override
    public void canReview(String userName, String storeName) {
        if (!userRepository.existsByUsername(userName)) {
            throw new IllegalArgumentException("사용자가 존재하지 않습니다.");
        }

        Reservation reservation = reservationRepository.findReservationByUserNameAndStoreName(userName, storeName)
                .orElseThrow(() -> new IllegalArgumentException("예약 내역이 존재하지 않습니다."));

        if (reservation.getVisitStatus() == VisitStatus.NOT_VISITED) {
            throw new IllegalArgumentException("매장을 방문하지 않아 권한이 없습니다.");
        }
    }


}
