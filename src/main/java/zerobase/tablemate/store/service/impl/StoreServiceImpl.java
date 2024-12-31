package zerobase.tablemate.store.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
}
