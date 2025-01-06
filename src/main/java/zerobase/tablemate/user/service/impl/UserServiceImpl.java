package zerobase.tablemate.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zerobase.tablemate.aop.exception.ErrorResponseException;
import zerobase.tablemate.user.domain.User;
import zerobase.tablemate.user.repository.UserRepository;
import zerobase.tablemate.user.service.UserService;
import zerobase.tablemate.user.type.UserType;

import static zerobase.tablemate.aop.exception.ErrorCode.USER_ALREADY_EXIST;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User userRegister(String username, String password, String email, String phone, UserType userType, Boolean partnerMember) {
        canRegister(username);
        String passwordHash = passwordEncoder.encode(password);
        return userRepository.save(User.builder()
                .username(username)
                .password(passwordHash)
                .email(email)
                .phone(phone)
                .userType(userType)
                .partnerMember(partnerMember)
                .build());
    }

    @Override
    public void canRegister(String userName) {
        if (userRepository.existsByUsername(userName)) {
            throw new ErrorResponseException(USER_ALREADY_EXIST);
        }
    }
}
