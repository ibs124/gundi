package ibs124.gundi.security;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.ott.GenerateOneTimeTokenRequest;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.authentication.ott.OneTimeTokenAuthenticationToken;
import org.springframework.security.authentication.ott.OneTimeTokenService;
import org.springframework.stereotype.Component;

import ibs124.gundi.exception.ResourceReadingException;
import ibs124.gundi.model.application.dto.TokenDto;
import ibs124.gundi.model.persistence.UserEntity;
import ibs124.gundi.model.persistence.VerificationTokenEntity;
import ibs124.gundi.repository.UserRepository;
import ibs124.gundi.repository.VerificationTokenRepository;
import ibs124.gundi.service.auth.VerificationTokenConfigService;
import ibs124.gundi.util.TestUtils;
import ibs124.gundi.service.auth.UserVerifyingService;

@Component
public class MyOttService implements OneTimeTokenService {

    private final UserVerifyingService verificationService;
    private final VerificationTokenConfigService tokenCreatingService;
    private final VerificationTokenRepository tokenRepository;
    private final UserRepository userRepository;

    public MyOttService(UserVerifyingService verificationService,
            VerificationTokenConfigService tokenCreatingService,
            VerificationTokenRepository tokenRepository, UserRepository userRepository) {
        this.verificationService = verificationService;
        this.tokenCreatingService = tokenCreatingService;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    @Override
    public @Nullable OneTimeToken consume(OneTimeTokenAuthenticationToken authToken) {
        TokenDto token = this.verificationService
                .verifyBySecret(authToken.getTokenValue());

        return token == null
                ? null
                : new MyOtt(token.username(), token.secret(), token.expiresAt());
    }

    @Override
    public OneTimeToken generate(GenerateOneTimeTokenRequest request) {
        String username = request.getUsername();

        TokenDto tokenDto = this.tokenCreatingService.configureNewUserVerificationToken();

        UserEntity user = this.userRepository
                .findByUsernameOrPrimaryEmail(username, username)
                .orElseThrow(() -> new ResourceReadingException());

        VerificationTokenEntity token = this.tokenRepository
                .findByUser(user)
                .map(x -> TestUtils.updateLazyBy(tokenDto, x))
                .orElse(TestUtils.createBy(user, tokenDto));

        token = this.tokenRepository.save(token);

        return new MyOtt(user.getPrimaryEmail(), token.getSecret(), token.getExpiresAt());
    }

}
