package zerobase.tablemate.store.service;

import zerobase.tablemate.store.domain.Store;
import zerobase.tablemate.user.domain.User;

public interface StoreService {

    // 매장 등록
    Store storeRegister(String storeName, String storeAddress, String storePhone, String userId);
    // 매장 삭제
    Store storeDelete(String storeName, String userName);
    // 매장 정보 수정
    Store storeUpdate(String storeName, String updateName, String updateAddress, String updatePhone, String userName);
    // 매장 등록 권한 확인
    void canRegister(String userName, String storeName);
    // 매장 수정 권한 확인
    Store canModify(String userName, String storeName);
}
