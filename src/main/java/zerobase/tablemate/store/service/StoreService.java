package zerobase.tablemate.store.service;

import zerobase.tablemate.store.domain.Store;

public interface StoreService {

    Store storeRegister(String storeName, String storeAddress, String storePhone, String userId);
}
