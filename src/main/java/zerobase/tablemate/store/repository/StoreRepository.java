package zerobase.tablemate.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.tablemate.store.domain.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

}
