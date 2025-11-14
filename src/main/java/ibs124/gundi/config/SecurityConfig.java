package ibs124.gundi.config;

import static ibs124.gundi.config.RouteConfig.*;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import ibs124.gundi.model.enumm.UserRoleType;

@EnableWebSecurity
@Configuration
class SecurityConfig {

    private final String jsessionid = "JSESSIONID";
    private final String subroutesMatcher = "/**";

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(x -> x
                        .requestMatchers(
                                PathRequest.toStaticResources().atCommonLocations())
                        .permitAll()

                        .requestMatchers(
                                INDEX,
                                AUTH + this.subroutesMatcher)
                        .permitAll()

                        .requestMatchers(USERS + this.subroutesMatcher)
                        .hasRole(UserRoleType.USER.name())

                        .requestMatchers(ADMINS + this.subroutesMatcher)
                        .hasRole(UserRoleType.ADMIN.name())

                        .requestMatchers(ROOT + this.subroutesMatcher)
                        .hasRole(UserRoleType.ROOT.name())

                        .anyRequest().authenticated())

                .formLogin(x -> x
                        .loginPage(LOGIN)
                        .defaultSuccessUrl(INDEX)
                        .failureForwardUrl(LOGIN_ERROR))

                .logout(x -> x
                        .logoutUrl(LOGOUT)
                        .logoutSuccessUrl(INDEX)
                        .invalidateHttpSession(true)
                        .deleteCookies(this.jsessionid))

                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
