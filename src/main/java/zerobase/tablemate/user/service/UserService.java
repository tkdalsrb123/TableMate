package zerobase.tablemate.user.service;

import zerobase.tablemate.user.domain.User;
import zerobase.tablemate.user.type.UserType;

public interface UserService {

    /**
     * 회원 등록
     */
    User userRegister(String username, String password, String email, String phone, UserType userType, Boolean partnerMember);

}
