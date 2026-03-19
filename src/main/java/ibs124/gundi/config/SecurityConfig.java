package ibs124.gundi.config;

import static ibs124.gundi.constant.Routes.*;
import static ibs124.gundi.constant.Env.SUBROUTE_MATCHER;

import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationManagerFactories;
import org.springframework.security.config.annotation.authorization.EnableMultiFactorAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.FactorGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import ibs124.gundi.constant.Env;
import ibs124.gundi.model.application.Role;

@EnableMultiFactorAuthentication(authorities = {})
@EnableWebSecurity
@Configuration
class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        var password = AuthorizationManagerFactories
                .multiFactor()
                .requireFactors(FactorGrantedAuthority.PASSWORD_AUTHORITY)
                .build();

        var mfa = AuthorizationManagerFactories
                .multiFactor()
                .requireFactors(
                        FactorGrantedAuthority.PASSWORD_AUTHORITY,
                        FactorGrantedAuthority.OTT_AUTHORITY)
                .build();

        return httpSecurity
                .authorizeHttpRequests(x -> x
                        .requestMatchers(
                                PathRequest.toStaticResources().atCommonLocations())
                        .permitAll()

                        .requestMatchers(INDEX, LOGIN, LOGIN_ERROR, REGISTER, REGISTER_SUCCESS)
                        .access(password.permitAll())

                        .requestMatchers(VERIFICATION + SUBROUTE_MATCHER)
                        .access(password.authenticated())

                        .requestMatchers(ROOT + SUBROUTE_MATCHER)
                        .access(mfa.hasAuthority(Role.ROOT.getAuthority()))

                        .requestMatchers(ADMINS + SUBROUTE_MATCHER)
                        .access(mfa.hasAuthority(Role.ADMIN.getAuthority()))

                        .requestMatchers(USERS + SUBROUTE_MATCHER)
                        .access(mfa.hasAuthority(Role.USER.getAuthority()))

                        .anyRequest().access(mfa.authenticated()))

                .formLogin(x -> x
                        .loginPage(LOGIN)
                        .defaultSuccessUrl(HOME)
                        .failureForwardUrl(LOGIN_ERROR))

                .oneTimeTokenLogin(x -> x
                        .loginPage(VERIFICATION_SEND)
                        .failureUrl(VERIFICATION_FAIL)
                        .showDefaultSubmitPage(false)
                        .defaultSuccessUrl(HOME))

                .logout(x -> x
                        .logoutUrl(LOGOUT)
                        .logoutSuccessUrl(INDEX)
                        .invalidateHttpSession(true)
                        .deleteCookies(Env.JSESSIONID))

                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
