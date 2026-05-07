package ibs124.gundi.config;

import static ibs124.gundi.constant.Routes.*;
import static ibs124.gundi.constant.Env.SUBROUTE_MATCHER;
import static org.springframework.security.core.authority.FactorGrantedAuthority.OTT_AUTHORITY;
import static org.springframework.security.core.authority.FactorGrantedAuthority.PASSWORD_AUTHORITY;

import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationManagerFactories;
import org.springframework.security.authorization.DefaultAuthorizationManagerFactory;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authorization.EnableMultiFactorAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.annotation.web.configurers.ott.OneTimeTokenLoginConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableMultiFactorAuthentication(authorities = {})
@EnableWebSecurity
@Configuration
class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(this.getAuthzHttpRequestsConfigurer())
                .formLogin(this.getFormLoginConfigurer())
                .oneTimeTokenLogin(this.getOttConfigurer())
                .logout(this.getLogoutConfigurer())
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>

    .AuthorizationManagerRequestMatcherRegistry> getAuthzHttpRequestsConfigurer() {

        var password = this.getAuthzManagerFactoryByFactors(PASSWORD_AUTHORITY);

        var mfa = this.getAuthzManagerFactoryByFactors(PASSWORD_AUTHORITY, OTT_AUTHORITY);

        return x -> x
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .permitAll()

                .requestMatchers(
                        INDEX, LOGIN, LOGIN_ERROR, REGISTER, REGISTER_SUCCESS,
                        PASSWORD_RESET + SUBROUTE_MATCHER)
                .access(password.permitAll())

                .requestMatchers(VERIFICATION + SUBROUTE_MATCHER)
                .access(password.authenticated())

                .requestMatchers(ROOT + SUBROUTE_MATCHER)
                .access(mfa.hasAuthority(AuthorityConfig.ROLE_ROOT.getAuthority()))

                .requestMatchers(ADMINS + SUBROUTE_MATCHER)
                .access(mfa.hasAuthority(AuthorityConfig.ROLE_ADMIN.getAuthority()))

                .requestMatchers(USERS + SUBROUTE_MATCHER)
                .access(mfa.hasAuthority(AuthorityConfig.ROLE_USER.getAuthority()))

                .anyRequest().access(mfa.authenticated());
    }

    public DefaultAuthorizationManagerFactory<Object> getAuthzManagerFactoryByFactors(
            String... factors) {
        return AuthorizationManagerFactories
                .multiFactor()
                .requireFactors(factors)
                .build();
    }

    public Customizer<FormLoginConfigurer<HttpSecurity>> getFormLoginConfigurer() {
        return x -> x
                .loginPage(LOGIN)
                .defaultSuccessUrl(HOME)
                .failureForwardUrl(LOGIN_ERROR);
    }

    public Customizer<OneTimeTokenLoginConfigurer<HttpSecurity>> getOttConfigurer() {
        return x -> x
                .loginPage(VERIFICATION_SEND)
                .failureUrl(VERIFICATION_ERROR)
                .showDefaultSubmitPage(false)
                .defaultSuccessUrl(HOME);
    }

    public Customizer<LogoutConfigurer<HttpSecurity>> getLogoutConfigurer() {
        return x -> x
                .logoutUrl(LOGOUT)
                .logoutSuccessUrl(INDEX);
    }
}
