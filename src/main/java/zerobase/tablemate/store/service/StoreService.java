package zerobase.tablemate.store.service;

import zerobase.tablemate.store.domain.Store;
import zerobase.tablemate.user.domain.User;

public interface StoreService {

    Store storeRegister(String storeName, String storeAddress, String storePhone, String userId);

    Store storeDelete(String storeName, String userName);

    Store storeUpdate(String storeName, String updateName, String updateAddress, String updatePhone, String userName);

    void canRegister(String userName, String storeName);

    Store canModify(String userName, String storeName);
}
