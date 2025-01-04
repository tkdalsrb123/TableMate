package zerobase.tablemate.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.tablemate.store.domain.Store;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> deleteStoreByStoreName(String storeName);

    Optional<Store> findStoreByStoreName(String preStoreName);

    Optional<Store> findByUserName(String username);
}
