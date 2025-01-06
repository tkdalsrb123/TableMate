package zerobase.tablemate.kiosk.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zerobase.tablemate.kiosk.dto.Admin;
import zerobase.tablemate.kiosk.dto.Visit;
import zerobase.tablemate.kiosk.service.KioskService;

@RestController
@RequiredArgsConstructor
public class KioskController {

    private final KioskService kioskService;

    // 키오스크에 관리자 등록
    @PostMapping("/kiosk/admin")
    public Admin.Response kioskAdmin(@RequestBody Admin.Request admin) {
        return Admin.Response.of(kioskService.registerManager(admin.getAdminName(), admin.getAdminPassword()));
    }

    // 키오스크에서 방문 등록
    @PutMapping("/kiosk/visit")
    public String visitCheck(@RequestBody Visit visitor, @RequestParam String storeName) {
        return kioskService.visitCheck(visitor.getVisitorName(), visitor.getVisitorPassword(), storeName);
    }

}
