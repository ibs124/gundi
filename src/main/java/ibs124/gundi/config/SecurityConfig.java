package ibs124.gundi.config;

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

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(x -> x
                        .requestMatchers(
                                PathRequest.toStaticResources().atCommonLocations())
                        .permitAll()

                        .requestMatchers(RouteConfig.GUEST_ROUTES_GROUP).permitAll()

                        .requestMatchers(RouteConfig.USER_ROUTES_GROUP)
                        .hasRole(UserRoleType.USER.name())

                        .requestMatchers(RouteConfig.ADMIN_ROUTES_GROUP)
                        .hasRole(UserRoleType.ADMIN.name())

                        .requestMatchers(RouteConfig.ROOT_ROUTES_GROUP)
                        .hasRole(UserRoleType.ROOT.name())

                        .anyRequest().authenticated())

                .formLogin(x -> x
                        .loginPage(RouteConfig.LOGIN)
                        .defaultSuccessUrl(RouteConfig.INDEX)
                        .failureForwardUrl(RouteConfig.LOGIN_ERROR))

                .logout(x -> x
                        .logoutUrl(RouteConfig.LOGOUT)
                        .logoutSuccessUrl(RouteConfig.INDEX)
                        .invalidateHttpSession(true)
                        .deleteCookies(jsessionid))

                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
