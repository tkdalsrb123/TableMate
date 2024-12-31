package zerobase.tablemate.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.tablemate.user.domain.User;
import zerobase.tablemate.user.repository.UserRepository;
import zerobase.tablemate.user.service.UserService;
import zerobase.tablemate.user.type.UserType;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User userRegister(String username, String password, String email, String phone, UserType userType, Boolean partnerMember) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("이미 존재하는 사용자 이름입니다.");
        }
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        return userRepository.save(User.builder()
                .username(username)
                .password(password)
                .email(email)
                .phone(phone)
                .userType(userType)
                .partnerMember(partnerMember)
                .build());
    }
}
