package ibs124.gundi.security;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.ott.GenerateOneTimeTokenRequest;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.authentication.ott.OneTimeTokenAuthenticationToken;
import org.springframework.security.authentication.ott.OneTimeTokenService;

import ibs124.gundi.model.application.TokenDto;
import ibs124.gundi.repository.VerificationTokenRepository;
import ibs124.gundi.service.auth.NewUserVerificationTokenCreatingService;
import ibs124.gundi.service.auth.VerificationService;

public class MyOttService implements OneTimeTokenService {

    private final VerificationService verificationService;
    private final NewUserVerificationTokenCreatingService tokenCreatingService;
    private final VerificationTokenRepository tokenRepository;

    public MyOttService(VerificationService verificationService,
            NewUserVerificationTokenCreatingService tokenCreatingService, VerificationTokenRepository tokenRepository) {
        this.verificationService = verificationService;
        this.tokenCreatingService = tokenCreatingService;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public @Nullable OneTimeToken consume(OneTimeTokenAuthenticationToken authenticationToken) {
        boolean success = this.verificationService
                .verifyBySecret(authenticationToken.getTokenValue());

        if (!success) {
            return null;
        }

        return new MyOtt(
                authenticationToken.getName(),
                authenticationToken.getTokenValue(),
                null);
    }

    @Override
    public OneTimeToken generate(GenerateOneTimeTokenRequest request) {
        TokenDto response = this.tokenCreatingService.createNewUserVerificationToken();

        return null;
    }

}
