package zerobase.tablemate.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import zerobase.tablemate.user.domain.User;
import zerobase.tablemate.user.type.UserType;


public class UserDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
        @Email
        private String email;
        @NotBlank
        private String phone;
        @NotNull
        private UserType userType;

        private Boolean partnerMember = false;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class Response {
        private String username;
        private String email;
        private String phone;
        private UserType userType;

        public static Response of(User user) {
            return Response.builder()
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .userType(user.getUserType())
                    .build();
        }
    }
}
