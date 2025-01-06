package zerobase.tablemate.user.service.impl;

import lombok.RequiredArgsConstructor;
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

    @Override
    public User userRegister(String username, String password, String email, String phone, UserType userType, Boolean partnerMember) {
        return userRepository.save(User.builder()
                .username(username)
                .password(password)
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
