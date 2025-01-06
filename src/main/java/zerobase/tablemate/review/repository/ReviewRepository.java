package zerobase.tablemate.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.tablemate.review.domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
