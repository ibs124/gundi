package ibs124.gundi.configuration;

import static ibs124.gundi.constant.Routes.*;
import static ibs124.gundi.constant.Constants.SUBROUTE_MATCHER;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import ibs124.gundi.constant.Constants;
import ibs124.gundi.model.enumm.RoleName;

@EnableWebSecurity
@Configuration
class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(x -> x
                        .requestMatchers(
                                PathRequest.toStaticResources().atCommonLocations())
                        .permitAll()

                        .requestMatchers(
                                INDEX,
                                AUTH + SUBROUTE_MATCHER)
                        .permitAll()

                        .requestMatchers(USERS + SUBROUTE_MATCHER)
                        .hasRole(RoleName.USER.name())

                        .requestMatchers(ADMINS + SUBROUTE_MATCHER)
                        .hasRole(RoleName.ADMIN.name())

                        .requestMatchers(ROOT + SUBROUTE_MATCHER)
                        .hasRole(RoleName.ROOT.name())

                        .anyRequest().authenticated())

                .formLogin(x -> x
                        .loginPage(LOGIN)
                        .defaultSuccessUrl(USERS_SELF_PROFILE)
                        .failureForwardUrl(LOGIN_ERROR))

                .logout(x -> x
                        .logoutUrl(LOGOUT)
                        .logoutSuccessUrl(INDEX)
                        .invalidateHttpSession(true)
                        .deleteCookies(Constants.JSESSIONID))

                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
