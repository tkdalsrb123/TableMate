package zerobase.tablemate.store.dto;

import lombok.*;
import zerobase.tablemate.store.domain.Store;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreDeleteDto {
    private String storeName;
    private String userName;

    public static StoreDeleteDto from(Store store) {
        return StoreDeleteDto.builder()
                .storeName(store.getStoreName())
                .userName(store.getUserName())
                .build();
    }
}
