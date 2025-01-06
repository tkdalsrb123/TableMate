package zerobase.tablemate.store.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.tablemate.aop.exception.ErrorResponseException;
import zerobase.tablemate.store.domain.Store;
import zerobase.tablemate.store.repository.StoreRepository;
import zerobase.tablemate.store.service.StoreService;
import zerobase.tablemate.user.domain.User;
import zerobase.tablemate.user.repository.UserRepository;
import zerobase.tablemate.user.type.UserType;

import static zerobase.tablemate.aop.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    @Override
    public Store storeRegister(String storeName, String storeAddress, String storePhone, String userName) {
        canRegister(userName, storeName);
        return storeRepository.save(Store.builder()
                .userName(userName)
                .storeName(storeName)
                .storeAddress(storeAddress)
                .storePhone(storePhone)
                .build());
    }

    @Override
    @Transactional
    public Store storeDelete(String storeName, String userName) {
        Store store = canModify(userName, storeName);
        storeRepository.delete(store);
        return store;
    }

    @Override
    @Transactional
    public Store storeUpdate(String storeName, String updateName, String updateAddress, String updatePhone, String userName) {
        Store store = canModify(userName, storeName);

        store.setStoreName(updateName);
        store.setStoreAddress(updateAddress);
        store.setStorePhone(updatePhone);
        return storeRepository.save(store);
    }

    @Override
    public void canRegister(String userName, String storeName) {
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new ErrorResponseException(USER_NOT_FOUND));

        if (user.getUserType() != UserType.MANAGER || Boolean.FALSE.equals(user.isPartnerMember())) {
            throw new ErrorResponseException(STORE_ACCESS_DENIED);
        }

        if (storeRepository.existsByStoreName(storeName)) {
            throw new ErrorResponseException(STORE_ALREADY_EXISTS);
        }
    }

    @Override
    public Store canModify(String userName, String storeName) {
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new ErrorResponseException(USER_NOT_FOUND));

        if (user.getUserType() != UserType.MANAGER || user.isPartnerMember()) {
            throw new ErrorResponseException(STORE_ACCESS_DENIED);
        }

        Store store = storeRepository.findStoreByStoreName(storeName).orElseThrow(() -> new ErrorResponseException(STORE_NOT_FOUND));

        if (!store.getUserName().equals(userName)) {
            throw new ErrorResponseException(STORE_ACCESS_DENIED);
        }

        return store;
    }


}
