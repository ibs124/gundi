package ibs124.gundi.security;

import java.time.Instant;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.ott.GenerateOneTimeTokenRequest;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.authentication.ott.OneTimeTokenAuthenticationToken;
import org.springframework.security.authentication.ott.OneTimeTokenService;
import org.springframework.stereotype.Component;

import ibs124.gundi.service.auth.VerificationTokenCreatingService;
import ibs124.gundi.model.dto.TokenDto;
import ibs124.gundi.model.dto.UserVerifiedResponseDto;
import ibs124.gundi.service.auth.UserVerifyingService;

@Component
public class MyOttService implements OneTimeTokenService {

    private final UserVerifyingService verificationService;
    private final VerificationTokenCreatingService tokenCreatingService;

    public MyOttService(
            UserVerifyingService verificationService,
            VerificationTokenCreatingService tokenCreatingService) {
        this.verificationService = verificationService;
        this.tokenCreatingService = tokenCreatingService;
    }

    @Override
    public @Nullable OneTimeToken consume(OneTimeTokenAuthenticationToken authToken) {
        UserVerifiedResponseDto response = this.verificationService
                .verifyBySecret(authToken.getTokenValue());

        String username = response.user().username();
        String secret = response.token().secret();
        Instant expiresAt = response.token().expiresAt();

        return response == null ? null : this.map(username, secret, expiresAt);
    }

    @Override
    public OneTimeToken generate(GenerateOneTimeTokenRequest request) {
        TokenDto token = this.tokenCreatingService
                .createByUsername(request.getUsername());

        return this.map(request.getUsername(), token.secret(), token.expiresAt());
    }

    private OneTimeToken map(String username, String secret, Instant expiresAt) {
        return new OneTimeToken() {
            @Override
            public String getTokenValue() {
                return secret;
            }

            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public Instant getExpiresAt() {
                return expiresAt;
            }
        };
    }

}
