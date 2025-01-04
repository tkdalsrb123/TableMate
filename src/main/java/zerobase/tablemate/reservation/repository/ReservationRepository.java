package zerobase.tablemate.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.tablemate.reservation.domain.Reservation;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsReservationByReservationDateTime(LocalDateTime reservationDateTime);

    Optional<Reservation> findReservationByUserNameAndStoreName(String username, String storeName);
}
