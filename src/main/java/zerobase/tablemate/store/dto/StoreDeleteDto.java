package zerobase.tablemate.store.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreDeleteDto {
    private String storeName;
    private String userName;
    private String password;
}
