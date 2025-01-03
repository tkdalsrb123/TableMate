package zerobase.tablemate.store.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.tablemate.store.domain.Store;
import zerobase.tablemate.store.repository.StoreRepository;
import zerobase.tablemate.store.service.StoreService;
import zerobase.tablemate.user.domain.User;
import zerobase.tablemate.user.repository.UserRepository;
import zerobase.tablemate.user.type.UserType;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    @Override
    public Store storeRegister(String storeName, String storeAddress, String storePhone, String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        if (user.getUserType() != UserType.MANAGER || Boolean.FALSE.equals(user.isPartnerMember())) {
            throw new IllegalArgumentException("가게를 등록할 권한이 없습니다.");
        }

        return storeRepository.save(Store.builder()
                .userName(userName)
                .storeName(storeName)
                .storeAddress(storeAddress)
                .storePhone(storePhone)
                .build());
    }

    @Override
    @Transactional
    public String storeDelete(String storeName, String userName, String password) {
        User user = userRepository.findByUsernameAndPassword(userName, password).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        storeRepository.deleteStoreByStoreName(storeName).orElseThrow(() -> new IllegalArgumentException("등록되지 않는 매장입니다."));
        return String.format("%s님의 %s 매장이 삭제 완료되었습니다.", userName, storeName);
    }

    @Override
    @Transactional
    public String storeUpdate(String storeName, String updateName, String updateAddress, String updatePhone, String userName, String password) {
        User user = userRepository.findByUsernameAndPassword(userName, password).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Store store = storeRepository.findStoreByStoreName(storeName).orElseThrow(() -> new IllegalArgumentException("등록되지 않은 매장입니다."));
        String category = "", preString = "", postString = "";
        if (updateName != null) {
            category = "매장명";
            preString = store.getStoreName();
            postString = updateName;
            store.setStoreName(updateName);
        }
        if (updateAddress != null) {
            category = "매장주소";
            preString = store.getStoreAddress();
            postString = updateAddress;
            store.setStoreAddress(updateAddress);
        }
        if (updatePhone != null) {
            category = "매장번호";
            preString = store.getStorePhone();
            postString = updatePhone;
            store.setStorePhone(updatePhone);
        }

        return String.format("%s가 %s가 %s로 변경되었습니다.", category, preString, postString);
    }

}
