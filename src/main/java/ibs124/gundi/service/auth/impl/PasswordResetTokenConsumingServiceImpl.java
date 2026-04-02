package ibs124.gundi.service.auth.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ibs124.gundi.model.dto.auth.PasswordResetDto;
import ibs124.gundi.model.entity.PasswordResetTokenEntity;
import ibs124.gundi.model.entity.UserEntity;
import ibs124.gundi.repository.PasswordResetTokenRepository;
import ibs124.gundi.repository.UserRepository;
import ibs124.gundi.service.auth.PasswordResetTokenConsumingService;
import jakarta.validation.Validator;

@Service
class PasswordResetTokenConsumingServiceImpl
        extends AbstractTokenConsumingService<PasswordResetTokenEntity>
        implements PasswordResetTokenConsumingService {

    private final Validator validator;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public PasswordResetTokenConsumingServiceImpl(
            PasswordResetTokenRepository passwordResetTokenRepository,
            Validator validator,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository) {

        super(passwordResetTokenRepository, validator);

        this.validator = validator;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public boolean consume(PasswordResetDto request) {

        if (!this.requestIsValid(request)) {
            return false;
        }

        PasswordResetTokenEntity token = super.consumeTokenBySecret(request.secret());

        if (token == null) {
            return false;
        }

        UserEntity user = token.getUser();

        if (user.getPassword().equals(request.password())) {
            return true;
        }

        String encodedPassword = this.passwordEncoder.encode(request.password());

        user.setPassword(encodedPassword);

        user = this.userRepository.save(user);

        return true;
    }

    private boolean requestIsValid(PasswordResetDto dto) {
        return dto != null && this.validator.validate(dto).isEmpty();
    }

}