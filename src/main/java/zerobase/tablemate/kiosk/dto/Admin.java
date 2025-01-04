package zerobase.tablemate.kiosk.dto;

import lombok.*;
import zerobase.tablemate.store.domain.Store;


public class Admin {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private String adminName;
        private String adminPassword;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private String adminName;
        private String storeName;
        private String storeAddress;
        private String storePhone;

        public static Response of(Store store) {
            return Response.builder()
                    .adminName(store.getUserName())
                    .storeName(store.getStoreName())
                    .storeAddress(store.getStoreAddress())
                    .storePhone(store.getStorePhone())
                    .build();
        }
    }

}
