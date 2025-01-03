package zerobase.tablemate.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zerobase.tablemate.reservation.dto.ReservationRegisterDto;
import zerobase.tablemate.reservation.service.ReservationService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/reservation/register")
    public ReservationRegisterDto.Response reservationRegister(@RequestBody ReservationRegisterDto.Request request) {
        String userName = request.getUserName();
        String storeName = request.getStoreName();
        LocalDateTime reservationDateTime = LocalDateTime.parse(request.getReservationDateTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return ReservationRegisterDto.Response.of(reservationService.reservationRegister(userName, storeName, reservationDateTime));
    }
}
