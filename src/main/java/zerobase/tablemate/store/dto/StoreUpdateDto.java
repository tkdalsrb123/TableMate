package zerobase.tablemate.store.dto;

import lombok.*;

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
    private String password;
}
