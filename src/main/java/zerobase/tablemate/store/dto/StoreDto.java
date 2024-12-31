package zerobase.tablemate.store.dto;

import lombok.*;
import zerobase.tablemate.store.domain.Store;

public class StoreDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private String storeName;
        private String storeAddress;
        private String storePhone;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private String userName;
        private String storeName;
        private String storeAddress;
        private String storePhone;

        public static Response of(Store store) {
            return Response.builder()
                    .userName(store.getUserName())
                    .storeName(store.getStoreName())
                    .storeAddress(store.getStoreAddress())
                    .storePhone(store.getStorePhone())
                    .build();
        }
    }
}
