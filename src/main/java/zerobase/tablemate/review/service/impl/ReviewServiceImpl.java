package zerobase.tablemate.review.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.tablemate.aop.exception.ErrorCode;
import zerobase.tablemate.aop.exception.ErrorResponseException;
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

import static zerobase.tablemate.aop.exception.ErrorCode.*;

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
            throw new ErrorResponseException(USER_ALREADY_EXIST);
        }

        Reservation reservation = reservationRepository.findReservationByUserNameAndStoreName(userName, storeName)
                .orElseThrow(() -> new ErrorResponseException(RESERVATION_NOT_FOUND));

        if (reservation.getVisitStatus() == VisitStatus.NOT_VISITED) {
            throw new ErrorResponseException(REVIEW_ACCESS_DENIED);
        }

    }

    @Override
    @Transactional
    public Review reviewModify(String userName, String storeName, String title, String content) {
        canReview(userName, storeName);
        Review review = reviewRepository.findByUserNameAndStoreName(userName, storeName)
                .orElseThrow(() -> new ErrorResponseException(REVIEW_NOT_FOUND));
        review.setTitle(title);
        review.setContent(content);

        return review;
    }

    @Override
    @Transactional
    public Review reviewDelete(String userName, String storeName, Long reviewId) {
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new ErrorResponseException(USER_NOT_FOUND));
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ErrorResponseException(REVIEW_NOT_FOUND));

        if (user.getUserType() == UserType.MANAGER  && user.isPartnerMember()) {
            if (!storeRepository.existsByUserNameAndStoreName(userName, storeName)) {
                throw new ErrorResponseException(REVIEW_ACCESS_DENIED);
            }

            reviewRepository.deleteById(reviewId);
            return review;
        }

        canReview(userName, storeName);
        reviewRepository.deleteById(reviewId);
        return review;
    }


}
