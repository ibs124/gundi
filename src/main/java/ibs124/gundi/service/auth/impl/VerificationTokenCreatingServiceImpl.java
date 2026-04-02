package ibs124.gundi.service.auth.impl;

import org.springframework.stereotype.Service;

import ibs124.gundi.model.dto.auth.TokenDto;
import ibs124.gundi.model.entity.UserEntity;
import ibs124.gundi.model.entity.VerificationTokenEntity;
import ibs124.gundi.repository.UserRepository;
import ibs124.gundi.repository.VerificationTokenRepository;
import ibs124.gundi.service.auth.VerificationTokenGeneratingService;
import ibs124.gundi.service.auth.VerificationTokenCreatingService;
import jakarta.validation.Validator;

@Service
class VerificationTokenCreatingServiceImpl
        extends AbstractTokenCreatingService<VerificationTokenEntity>
        implements VerificationTokenCreatingService {

    private final VerificationTokenGeneratingService tokenCreatingService;
    private final VerificationTokenRepository tokenRepository;
    private final UserRepository userRepository;

    public VerificationTokenCreatingServiceImpl(
            VerificationTokenGeneratingService tokenConfiguringService,
            VerificationTokenRepository tokenRepository,
            UserRepository userRepository,
            Validator validator) {

        super(validator, tokenRepository);

        this.tokenCreatingService = tokenConfiguringService;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    @Override
    VerificationTokenEntity construct(UserEntity user) {
        return new VerificationTokenEntity(user);
    }

    @Override
    TokenDto generateToken() {
        return this.tokenCreatingService.generateVerificationToken();
    }

    @Override
    public TokenDto createById(Long id) {
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
    public TokenDto createByUsername(String username) {
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
