package ibs124.gundi.service.auth.impl;

import org.springframework.stereotype.Service;

import ibs124.gundi.common.token_generator.TokenGenerator;
import ibs124.gundi.constant.Env;
import ibs124.gundi.model.dto.auth.TokenContract;
import ibs124.gundi.model.entity.UserEntity;
import ibs124.gundi.model.entity.VerificationTokenEntity;
import ibs124.gundi.repository.UserRepository;
import ibs124.gundi.repository.VerificationTokenRepository;
import ibs124.gundi.service.auth.VerificationTokenCreatingService;
import jakarta.validation.Validator;

@Service
class VerificationTokenCreatingServiceImpl
        extends AbstractTokenCreatingService<VerificationTokenEntity>
        implements VerificationTokenCreatingService {

    private final VerificationTokenRepository tokenRepository;
    private final UserRepository userRepository;

    public VerificationTokenCreatingServiceImpl(
            VerificationTokenRepository tokenRepository,
            UserRepository userRepository,
            Validator validator,
            TokenGenerator tokenGenerator) {

        super(validator, tokenGenerator, tokenRepository);

        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    @Override
    VerificationTokenEntity getNewToken(UserEntity user) {
        return new VerificationTokenEntity(user);
    }

    @Override
    String getTokenGenerationSecret() {
        return Env.REQUEST_KEY_VERIFICATION;
    }

    @Override
    public TokenContract createById(Long id) {
        VerificationTokenEntity token = this.tokenRepository
                .findById(id)
                .orElse(null);

        if (token != null) {
            return super.respondWithTokenRepair(token);
        }

        try {
            UserEntity user = this.userRepository.getReferenceById(id);
            token = super.createNewToken(user);
            return super.mapToDto(token, null);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public TokenContract create(String username) {
        VerificationTokenEntity token = this.tokenRepository
                .findByUserUsernameOrUserPrimaryEmail(username, username)
                .orElse(null);

        if (token != null) {
            return super.respondWithTokenRepair(token);
        }

        UserEntity user = this.userRepository
                .findByUsernameOrPrimaryEmail(username, username)
                .orElse(null);

        return super.respondWithNewToken(user);
    }
}
