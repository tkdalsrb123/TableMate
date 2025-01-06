package zerobase.tablemate.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    // 로그인 관리자 권한 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((requests) ->
                        requests
                                .requestMatchers(
                                        "/user/register",
                                        "/user/login").permitAll()
                                .requestMatchers("/store/**", "/review/**", "/kiosk/**").hasRole("MANAGER")
                                .requestMatchers("/reservation/**", "/review/**", "/kiosk/**").hasRole("CUSTOMER")
                                .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/user/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/user/login?error=true")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .permitAll()
                )
                .exceptionHandling((exception) -> exception
                        .accessDeniedPage("/error/denied")
                );

        return http.build();
    }

    // 비밀번호 인코딩
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
