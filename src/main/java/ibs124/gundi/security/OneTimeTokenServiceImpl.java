package ibs124.gundi.security;

import java.time.Instant;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.ott.GenerateOneTimeTokenRequest;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.authentication.ott.OneTimeTokenAuthenticationToken;
import org.springframework.security.authentication.ott.OneTimeTokenService;
import org.springframework.stereotype.Component;

import ibs124.gundi.service.auth.VerificationTokenCreatingService;
import ibs124.gundi.model.dto.auth.TokenDto;
import ibs124.gundi.service.auth.VerificationTokenConsumingService;

@Component
public class OneTimeTokenServiceImpl implements OneTimeTokenService {

    private final VerificationTokenConsumingService verificationService;
    private final VerificationTokenCreatingService tokenCreatingService;

    public OneTimeTokenServiceImpl(VerificationTokenConsumingService verificationService,
            VerificationTokenCreatingService tokenCreatingService) {
        this.verificationService = verificationService;
        this.tokenCreatingService = tokenCreatingService;
    }

    @Override
    public @Nullable OneTimeToken consume(OneTimeTokenAuthenticationToken authToken) {
        TokenDto token = this.verificationService
                .consume(() -> authToken.getTokenValue());

        return this.map(token);
    }

    @Override
    public OneTimeToken generate(GenerateOneTimeTokenRequest request) {
        TokenDto token = this.tokenCreatingService
                .create(request.getUsername());

        return this.map(token);
    }

    private OneTimeToken map(TokenDto dto) {
        if (dto == null) {
            return null;
        }
        return new OneTimeToken() {
            @Override
            public String getUsername() {
                return dto.username();
            }

            @Override
            public String getTokenValue() {
                return dto.secret();
            }

            @Override
            public Instant getExpiresAt() {
                return dto.expiresAt();
            }
        };
    }

}
