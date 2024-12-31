package zerobase.tablemate.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zerobase.tablemate.store.dto.StoreDto;
import zerobase.tablemate.store.service.StoreService;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/store/register")
    public StoreDto.Response storeRegister(@RequestBody StoreDto.Request request, @RequestParam String userName) {

        return StoreDto.Response.of(
                storeService.storeRegister(
                        request.getStoreName(),
                        request.getStoreAddress(),
                        request.getStorePhone(),
                        userName
                )
        );
    }
}
