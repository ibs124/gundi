package ibs124.gundi.security;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.ott.GenerateOneTimeTokenRequest;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.authentication.ott.OneTimeTokenAuthenticationToken;
import org.springframework.security.authentication.ott.OneTimeTokenService;
import org.springframework.stereotype.Component;

import ibs124.gundi.exception.ResourceReadingException;
import ibs124.gundi.model.application.TokenDto;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.domain.VerificationToken;
import ibs124.gundi.repository.UserRepository;
import ibs124.gundi.repository.VerificationTokenRepository;
import ibs124.gundi.service.auth.VerificationTokenConfiguringService;
import ibs124.gundi.util.TestUtils;
import ibs124.gundi.service.auth.VerificationService;

@Component
public class MyOttService implements OneTimeTokenService {

    private final VerificationService verificationService;
    private final VerificationTokenConfiguringService tokenCreatingService;
    private final VerificationTokenRepository tokenRepository;
    private final UserRepository userRepository;

    public MyOttService(VerificationService verificationService,
            VerificationTokenConfiguringService tokenCreatingService,
            VerificationTokenRepository tokenRepository, UserRepository userRepository) {
        this.verificationService = verificationService;
        this.tokenCreatingService = tokenCreatingService;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
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
        String username = request.getUsername();

        TokenDto tokenDto = this.tokenCreatingService.configureNewUserVerificationToken();

        User user = this.userRepository
                .findByUsernameOrPrimaryEmail(username, username)
                .orElseThrow(() -> new ResourceReadingException());

        VerificationToken token = this.tokenRepository
                .findByUser(user)
                .map(x -> TestUtils.updateLazyBy(tokenDto, x))
                .orElse(TestUtils.createBy(user, tokenDto));

        token = this.tokenRepository.save(token);

        return new MyOtt(user.getPrimaryEmail(), token.getSecret(), token.getExpiresAt());
    }

}
