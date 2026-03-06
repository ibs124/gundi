package ibs124.gundi.service.auth.impl;

import java.time.Instant;

import org.springframework.stereotype.Service;

import ibs124.gundi.model.domain.Email;
import ibs124.gundi.model.domain.Role;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.domain.VerificationToken;
import ibs124.gundi.model.enumm.RoleName;
import ibs124.gundi.repository.EmailRepository;
import ibs124.gundi.repository.RoleRepository;
import ibs124.gundi.repository.VerificationTokenRepository;
import ibs124.gundi.service.auth.VerificationService;
import jakarta.transaction.Transactional;

@Service
class VerificationServiceImpl implements VerificationService {

    private final VerificationTokenRepository tokenRepository;
    private final RoleRepository roleRepository;
    private final EmailRepository emailRepository;

    public VerificationServiceImpl(
            VerificationTokenRepository tokenRepository,
            RoleRepository roleRepository,
            EmailRepository emailRepository) {
        this.tokenRepository = tokenRepository;
        this.roleRepository = roleRepository;
        this.emailRepository = emailRepository;
    }

    @Override
    @Transactional
    public boolean verifyBySecret(String request) {
        VerificationToken token = this.tokenRepository
                .findBySecretAndExpiresAtAfter(request, Instant.now())
                .orElse(null);

        if (token == null) {
            return false;
        }

        User user = token.getUser();

        this.tokenRepository.delete(token);

        if (!user.isEnabled() && user.getLastVerifiedAt() == null) {
            this.verifyNewUser(user);
        }

        this.verifyNewUser(user);

        return true;
    }

    private void verifyNewUser(User user) {
        Role userRole = this.roleRepository
                .getReferenceById(RoleName.USER.ordinal() + 1L);

        user.addRole(userRole);

        user.setEnabled(true);

        user.setLastVerifiedAt(Instant.now());

        Email email = new Email(user, user.getPrimaryEmail());

        email.setLastVerifiedAt(user.getLastVerifiedAt());

        email = this.emailRepository.save(email);
    }

}
