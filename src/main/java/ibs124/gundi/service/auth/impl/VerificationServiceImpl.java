package ibs124.gundi.service.auth.impl;

import java.time.Instant;

import org.springframework.stereotype.Service;

import ibs124.gundi.model.domain.Email;
import ibs124.gundi.model.domain.Role;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.enumm.RoleName;
import ibs124.gundi.repository.EmailRepository;
import ibs124.gundi.repository.RoleRepository;
import ibs124.gundi.repository.VerificationTokenRepository;
import ibs124.gundi.service.auth.VerificationService;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;

@Service
class VerificationServiceImpl implements VerificationService {

    private final Validator validator;
    private final VerificationTokenRepository tokenRepository;
    private final RoleRepository roleRepository;
    private final EmailRepository emailRepository;

    public VerificationServiceImpl(
            Validator validator,
            VerificationTokenRepository tokenRepository,
            RoleRepository roleRepository,
            EmailRepository emailRepository) {
        this.validator = validator;
        this.tokenRepository = tokenRepository;
        this.roleRepository = roleRepository;
        this.emailRepository = emailRepository;
    }

    @Override
    @Transactional
    public boolean verifyBySecret(String request) {
        User user = this.tokenRepository
                .findBySecret(request)
                .filter(x -> this.validator.validate(x).isEmpty())
                .map(x -> {
                    this.tokenRepository.delete(x);
                    User u = x.getUser();
                    u.setLastVerifiedAt(Instant.now());
                    return u;
                })
                .orElse(null);

        if (user == null) {
            return false;
        }

        if (!user.isEnabled() && user.getLastVerifiedAt() == null) {
            this.verifyNewUser(user);
        }

        return true;
    }

    private void verifyNewUser(User user) {
        Role userRole = this.roleRepository
                .getReferenceById(RoleName.USER.ordinal() + 1L);

        user.addRole(userRole);

        user.setEnabled(true);

        Email email = new Email(user, user.getPrimaryEmail());

        email.setLastVerifiedAt(user.getLastVerifiedAt());

        email = this.emailRepository.save(email);
    }

}
