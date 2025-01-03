package zerobase.tablemate.store.service;

import zerobase.tablemate.store.domain.Store;

public interface StoreService {

    Store storeRegister(String storeName, String storeAddress, String storePhone, String userId);

    String storeDelete(String storeName, String userName, String password);

    String storeUpdate(String storeName, String updateName, String updateAddress, String updatePhone, String userName, String password);
}
