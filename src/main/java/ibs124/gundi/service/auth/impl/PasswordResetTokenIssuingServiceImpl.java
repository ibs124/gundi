package ibs124.gundi.service.auth.impl;

import org.springframework.stereotype.Service;

import ibs124.gundi.exception.ResourceReadingException;
import ibs124.gundi.model.dto.auth.TokenDto;
import ibs124.gundi.model.entity.UserEntity;
import ibs124.gundi.model.entity.VerificationTokenEntity;
import ibs124.gundi.repository.PasswordResetTokenRepository;
import ibs124.gundi.repository.UserRepository;
import ibs124.gundi.service.auth.PasswordResetTokenCreatingService;
import ibs124.gundi.service.auth.PasswordResetTokenIssuingService;
import ibs124.gundi.util.TestUtils;
import jakarta.validation.Validator;

@Service
class VerificationTokenIssuingServiceImpl implements PasswordResetTokenIssuingService {

    private final Validator validator;
    private final PasswordResetTokenRepository tokenRepository;
    private final PasswordResetTokenCreatingService tokenCreatingService;
    private final UserRepository userRepository;

    public VerificationTokenIssuingServiceImpl(
            Validator validator,
            PasswordResetTokenRepository tokenRepository,
            PasswordResetTokenCreatingService tokenCreatingService,
            UserRepository userRepository) {
        this.validator = validator;
        this.tokenRepository = tokenRepository;
        this.tokenCreatingService = tokenCreatingService;
        this.userRepository = userRepository;
    }

    @Override
    public TokenDto issueByUsername(String username) {
        TokenDto tokenDto = this.tokenRepository
                .findByUserUsernameOrUserPrimaryEmail(username, username)
                .filter(x -> x != null && this.validator.validate(x).isEmpty())
                .map(x -> new TokenDto(x.getSecret(), x.getExpiresAt()))
                .orElse(null);

        if (tokenDto != null) {
            return tokenDto;
        }

        UserEntity user = this.userRepository
                .findByUsernameOrPrimaryEmail(username, username)
                .orElseThrow(() -> new ResourceReadingException());

        return this.createNew(user);
    }

    private TokenDto createNew(UserEntity user) {
        TokenDto tokenDto = this.tokenCreatingService
                .createPasswordResetToken();

        VerificationTokenEntity token = TestUtils.createBy(user, tokenDto);

        token = this.tokenRepository.save(token);

        return new TokenDto(token.getSecret(), token.getExpiresAt());
    }

}
