package ibs124.gundi.service.auth.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ibs124.gundi.model.dto.auth.PasswordResetDto;
import ibs124.gundi.model.entity.PasswordResetTokenEntity;
import ibs124.gundi.model.entity.UserEntity;
import ibs124.gundi.repository.PasswordResetTokenRepository;
import ibs124.gundi.repository.UserRepository;
import ibs124.gundi.service.auth.PasswordResetService;
import jakarta.validation.Validator;

@Service
class PasswordResetServiceImpl implements PasswordResetService {

    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final Validator validator;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public PasswordResetServiceImpl(
            PasswordResetTokenRepository passwordResetTokenRepository,
            Validator validator,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.validator = validator;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public boolean resetPassword(PasswordResetDto request) {
        if (!this.isValid(request)) {
            return false;
        }

        PasswordResetTokenEntity token = this.passwordResetTokenRepository
                .findBySecret(request.secret())
                .orElse(null);

        if (this.isValid(token)) {
            return false;
        }

        this.passwordResetTokenRepository.delete(token);

        UserEntity user = token.getUser();

        if (user.getPassword().equals(request.password())) {
            return true;
        }

        String encodedPassword = this.passwordEncoder.encode(request.password());

        user.setPassword(encodedPassword);

        user = this.userRepository.save(user);

        return true;
    }

    private boolean isValid(PasswordResetTokenEntity token) {
        return token != null && this.validator.validate(token).isEmpty();
    }

    private boolean isValid(PasswordResetDto dto) {
        return dto != null && this.validator.validate(dto).isEmpty();
    }

}