package zerobase.tablemate.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zerobase.tablemate.user.dto.UserDto;
import zerobase.tablemate.user.service.impl.UserServiceImpl;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("user/register")
    public UserDto.Response registerUser(@RequestBody @Valid UserDto.Request request) {
        return UserDto.Response.of(userService.userRegister(
                request.getUsername(),
                request.getPassword(),
                request.getEmail(),
                request.getPhone(),
                request.getUserType(),
                request.getPartnerMember()));
    }
}
