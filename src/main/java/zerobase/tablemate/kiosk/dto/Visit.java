package zerobase.tablemate.kiosk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// 방문자 정보 DTO
public class Visit {
    private String visitorName;
    private String visitorPassword;
}
