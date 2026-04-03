package ibs124.gundi.service.auth.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ibs124.gundi.model.dto.auth.PasswordResetDto;
import ibs124.gundi.model.dto.auth.TokenContract;
import ibs124.gundi.model.entity.PasswordResetTokenEntity;
import ibs124.gundi.model.entity.UserEntity;
import ibs124.gundi.repository.PasswordResetTokenRepository;
import ibs124.gundi.repository.UserRepository;
import jakarta.validation.Validator;

@Service
class PasswordResetTokenConsumingServiceImpl
        extends AbstractTokenConsumingServiceImpl<PasswordResetTokenEntity, PasswordResetDto> {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public PasswordResetTokenConsumingServiceImpl(
            PasswordResetTokenRepository passwordResetTokenRepository,
            Validator validator,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository) {

        super(passwordResetTokenRepository, validator);

        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public TokenContract consume(PasswordResetDto request) {

        PasswordResetTokenEntity token = super.consumeAbstract(request);

        if (token == null) {
            return null;
        }

        UserEntity user = token.getUser();

        if (user.getPassword().equals(request.password()) == false) {
            String encodedPassword = this.passwordEncoder.encode(request.password());

            user.setPassword(encodedPassword);

            user = this.userRepository.save(user);
        }

        return super.mapToDto(token, user.getPrimaryEmail());
    }

}