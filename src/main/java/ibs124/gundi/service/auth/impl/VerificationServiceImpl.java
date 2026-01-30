package ibs124.gundi.service.auth.impl;

import java.time.Instant;

import org.springframework.stereotype.Service;

import ibs124.gundi.model.domain.Email;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.domain.VerificationToken;
import ibs124.gundi.model.enumm.RoleName;
import ibs124.gundi.model.enumm.VerificationType;
import ibs124.gundi.repository.EmailRepository;
import ibs124.gundi.repository.RoleRepository;
import ibs124.gundi.repository.UserRepository;
import ibs124.gundi.repository.VerificationTokenRepository;
import ibs124.gundi.service.auth.VerificationService;
import jakarta.transaction.Transactional;

@Service
class VerificationServiceImpl implements VerificationService {

    private final VerificationTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final EmailRepository emailRepository;

    public VerificationServiceImpl(
            VerificationTokenRepository tokenRepository,
            UserRepository userRepository,
            RoleRepository roleRepository,
            EmailRepository emailRepository) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.emailRepository = emailRepository;
    }

    @Override
    @Transactional
    public boolean verifyNewUserVerificationToken(String request) {
        VerificationToken token = this.tokenRepository
                .findByValueAndExpiresAtBefore(request, Instant.now())
                .orElse(null);

        if (token == null || token.getType() != VerificationType.NEW_USER) {
            return false;
        }

        User user = token.getUser();

        this.tokenRepository.delete(token);

        user.addRoles(
                this.roleRepository
                        .findByNameIn(RoleName.USER));

        user.setIsEnabled(true);

        user.setLastVerifiedAt(Instant.now());

        user = this.userRepository.save(user);

        Email email = new Email(user, user.getPrimaryEmail());
        email.setLastVerifiedAt(user.getLastVerifiedAt());

        email = this.emailRepository.save(email);

        return true;
    }

}
