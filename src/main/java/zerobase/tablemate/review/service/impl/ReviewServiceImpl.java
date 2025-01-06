package zerobase.tablemate.review.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.tablemate.reservation.domain.Reservation;
import zerobase.tablemate.reservation.repository.ReservationRepository;
import zerobase.tablemate.reservation.type.VisitStatus;
import zerobase.tablemate.review.domain.Review;
import zerobase.tablemate.review.repository.ReviewRepository;
import zerobase.tablemate.review.service.ReviewService;
import zerobase.tablemate.store.repository.StoreRepository;
import zerobase.tablemate.user.domain.User;
import zerobase.tablemate.user.repository.UserRepository;
import zerobase.tablemate.user.type.UserType;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final StoreRepository storeRepository;

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

    @Override
    public String reviewModify(String userName, String storeName, String title, String content) {
        canReview(userName, storeName);
        Review review = reviewRepository.findByUserNameAndStoreName(userName, storeName)
                .orElseThrow(() -> new IllegalArgumentException("리뷰가 존재하지 않습니다."));
        review.setTitle(title);
        review.setContent(content);

        return String.format("%s님이 작성하신 리뷰가 수정되었습니다.", userName);
    }

    @Override
    @Transactional
    public String reviewDelete(String userName, String storeName, Long reviewId) {
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
        if (user.getUserType() == UserType.MANAGER && user.isPartnerMember() && storeRepository.existsByUserNameAndStoreName(userName, storeName)) {
            reviewRepository.deleteById(reviewId);
            return String.format("%s님이 리뷰를 삭제하였습니다.", userName);
        }

        canReview(userName, storeName);
        reviewRepository.deleteById(reviewId);
        return String.format("%s님이 리뷰를 삭제하였습니다.", userName);
    }


}
