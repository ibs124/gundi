package ibs124.gundi.security;

import java.time.Instant;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.ott.GenerateOneTimeTokenRequest;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.authentication.ott.OneTimeTokenAuthenticationToken;
import org.springframework.security.authentication.ott.OneTimeTokenService;
import org.springframework.stereotype.Component;

import ibs124.gundi.service.auth.VerificationTokenCreatingService;
import ibs124.gundi.model.dto.auth.TokenConsumeRequest;
import ibs124.gundi.model.dto.auth.TokenContract;
import ibs124.gundi.service.auth.AbstractTokenConsumingService;

@Component
public class OneTimeTokenServiceImpl implements OneTimeTokenService {

    private final AbstractTokenConsumingService<TokenConsumeRequest> consumator;
    private final VerificationTokenCreatingService gnerator;

    public OneTimeTokenServiceImpl(
            AbstractTokenConsumingService<TokenConsumeRequest> consumator,
            VerificationTokenCreatingService gnerator) {
        this.consumator = consumator;
        this.gnerator = gnerator;
    }

    @Override
    public @Nullable OneTimeToken consume(OneTimeTokenAuthenticationToken authToken) {
        TokenContract token = this.consumator
                .consume(() -> authToken.getTokenValue());

        return this.map(token);
    }

    @Override
    public OneTimeToken generate(GenerateOneTimeTokenRequest request) {
        TokenContract token = this.gnerator
                .create(request.getUsername());

        return this.map(token);
    }

    private OneTimeToken map(TokenContract dto) {
        if (dto == null) {
            return null;
        }
        return new OneTimeToken() {
            @Override
            public String getUsername() {
                return dto.getUsername();
            }

            @Override
            public String getTokenValue() {
                return dto.getSecret();
            }

            @Override
            public Instant getExpiresAt() {
                return dto.getExpiresAt();
            }
        };
    }

}
