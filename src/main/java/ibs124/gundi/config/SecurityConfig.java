package ibs124.gundi.config;

import static ibs124.gundi.constant.Routes.*;
import static ibs124.gundi.constant.Env.SUBROUTE_MATCHER;

import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationManagerFactories;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authorization.EnableMultiFactorAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.FactorGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import ibs124.gundi.constant.Env;
import ibs124.gundi.model.enumm.RoleName;

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

                        .requestMatchers(INDEX, LOGIN, LOGIN_ERROR, REGISTER)
                        .access(password.permitAll())

                        .requestMatchers(VERIFICATION + SUBROUTE_MATCHER)
                        .access(password.authenticated())

                        .requestMatchers(ROOT + SUBROUTE_MATCHER)
                        .access(mfa.hasRole(RoleName.ROOT.name()))

                        .requestMatchers(ADMINS + SUBROUTE_MATCHER)
                        .access(mfa.hasRole(RoleName.ADMIN.name()))

                        .requestMatchers(USERS + SUBROUTE_MATCHER)
                        .access(mfa.hasRole(RoleName.USER.name()))

                        .anyRequest().access(mfa.authenticated()))

                .formLogin(x -> x
                        .loginPage(LOGIN)
                        .defaultSuccessUrl(HOME)
                        .failureForwardUrl(LOGIN_ERROR))

                .oneTimeTokenLogin(x -> x
                        .defaultSuccessUrl(HOME)
                        .tokenGeneratingUrl(VERIFICATION_SEND)
                        .defaultSubmitPageUrl(VERIFICATION_SUBMIT)
                        .showDefaultSubmitPage(false))

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
