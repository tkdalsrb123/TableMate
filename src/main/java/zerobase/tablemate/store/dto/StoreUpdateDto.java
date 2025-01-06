package zerobase.tablemate.store.dto;

import lombok.*;
import zerobase.tablemate.store.domain.Store;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreUpdateDto {
    private String storeName;
    private String updateName;
    private String updateAddress;
    private String updatePhone;

    private String userName;

    public static StoreUpdateDto from(Store store) {
        return StoreUpdateDto.builder()
                .updateName(store.getStoreName())
                .updateAddress(store.getStoreAddress())
                .updatePhone(store.getStorePhone())
                .build();
    }
}
