package ibs124.gundi.service.auth.impl;

import org.springframework.stereotype.Service;

import ibs124.gundi.model.application.TokenDto;
import ibs124.gundi.model.persistence.User;
import ibs124.gundi.model.persistence.VerificationToken;
import ibs124.gundi.repository.UserRepository;
import ibs124.gundi.repository.VerificationTokenRepository;
import ibs124.gundi.service.auth.VerificationTokenConfigService;
import ibs124.gundi.service.auth.VerificationTokenCreatingService;
import ibs124.gundi.util.TestUtils;

@Service
class VerificationTokenCreatingServiceImpl implements VerificationTokenCreatingService {

    private final VerificationTokenConfigService tokenConfiguringService;
    private final VerificationTokenRepository tokenRepository;
    private final UserRepository userRepository;

    public VerificationTokenCreatingServiceImpl(
            VerificationTokenConfigService tokenConfiguringService,
            VerificationTokenRepository tokenRepository,
            UserRepository userRepository) {
        this.tokenConfiguringService = tokenConfiguringService;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String createVerificationTokenByUserId(Long id) {
        VerificationToken token = this.tokenRepository
                .findByUserId(id)
                .filter(x -> TestUtils.isValid(x))
                .orElse(null);

        if (token != null) {
            return token.getSecret();
        }

        User user = this.userRepository.getReferenceById(id);

        TokenDto tokenDto = this.tokenConfiguringService
                .configureNewUserVerificationToken();

        token = TestUtils.createBy(user, tokenDto);

        token = this.tokenRepository.save(token);

        return token.getSecret();
    }

}
