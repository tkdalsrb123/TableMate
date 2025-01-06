package zerobase.tablemate.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zerobase.tablemate.store.dto.StoreDeleteDto;
import zerobase.tablemate.store.dto.StoreDto;
import zerobase.tablemate.store.dto.StoreUpdateDto;
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

    @DeleteMapping("/store/delete")
    public StoreDeleteDto deleteStore(@RequestBody StoreDeleteDto request) {
        return StoreDeleteDto.from(storeService.storeDelete(
                request.getStoreName(),
                request.getUserName())
        );
    }

    @PutMapping("/store/update")
    public StoreUpdateDto updateStore(@RequestBody StoreUpdateDto request) {
        return StoreUpdateDto.from(storeService.storeUpdate(
                request.getStoreName(),
                request.getUpdateName(),
                request.getUpdateAddress(),
                request.getUpdatePhone(),
                request.getUserName()
        ));
    }
}
