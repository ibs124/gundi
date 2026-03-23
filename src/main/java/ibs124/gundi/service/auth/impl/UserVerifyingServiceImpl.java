package ibs124.gundi.service.auth.impl;

import java.time.Instant;

import org.springframework.stereotype.Service;

import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.model.dto.TokenDto;
import ibs124.gundi.model.dto.UserDto;
import ibs124.gundi.model.dto.UserVerifiedResponseDto;
import ibs124.gundi.model.entity.EmailEntity;
import ibs124.gundi.model.entity.UserEntity;
import ibs124.gundi.model.entity.VerificationTokenEntity;
import ibs124.gundi.repository.EmailRepository;
import ibs124.gundi.repository.VerificationTokenRepository;
import ibs124.gundi.service.auth.UserVerifyingService;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;

@Service
class UserVerifyingServiceImpl implements UserVerifyingService {

    private final Validator validator;
    private final VerificationTokenRepository tokenRepository;
    private final EmailRepository emailRepository;
    private final UserMapper userMapper;

    public UserVerifyingServiceImpl(
            Validator validator,
            VerificationTokenRepository tokenRepository,
            EmailRepository emailRepository,
            UserMapper userMapper) {
        this.validator = validator;
        this.tokenRepository = tokenRepository;
        this.emailRepository = emailRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public UserVerifiedResponseDto verifyBySecret(String request) {
        VerificationTokenEntity token = this.consumeTokenBySecret(request);

        if (token == null) {
            return null;
        }

        UserEntity user = token.getUser();
        user.setLastMfaVerifiedAt(Instant.now());

        if (user.getLastMfaVerifiedAt() == null) {
            this.verifyNewUser(user);
        }

        UserDto userDto = this.userMapper.mapToDto(user);
        TokenDto tokenDto = this.userMapper.mapToDto(token);
        return new UserVerifiedResponseDto(userDto, tokenDto);
    }

    private VerificationTokenEntity consumeTokenBySecret(String secret) {
        VerificationTokenEntity token = this.tokenRepository
                .findBySecret(secret)
                .orElse(null);

        if (token == null) {
            return null;
        }

        boolean isTokenValid = this.validator.validate(token).isEmpty();

        if (!isTokenValid) {
            return null;
        }

        this.tokenRepository.delete(token);

        return token;
    }

    private void verifyNewUser(UserEntity user) {
        EmailEntity email = new EmailEntity(user, user.getPrimaryEmail());

        email.setLastVerifiedAt(user.getLastMfaVerifiedAt());

        email = this.emailRepository.save(email);
    }

}
