package ibs124.gundi.util;

import java.time.Instant;

import ibs124.gundi.event.AbstractVerificationEvent;
import ibs124.gundi.model.application.dto.TokenDto;
import ibs124.gundi.model.persistence.AbstractTokenEntity;
import ibs124.gundi.model.persistence.UserEntity;
import ibs124.gundi.model.persistence.VerificationTokenEntity;

public abstract class TestUtils {

    public static void sendVerification(AbstractVerificationEvent e) {
        String message = "%n[Verification Sent] type = %s , secret = %s , email = %s%n"
                .formatted(e.getClass().getName(), e.getSecret(), e.getEmail());
        System.out.println(message);
    }

    public static final VerificationTokenEntity createBy(UserEntity user, TokenDto dto) {
        VerificationTokenEntity token = createBy(user);
        return updateBy(dto, token);
    }

    public static VerificationTokenEntity createBy(UserEntity user) {
        VerificationTokenEntity token = new VerificationTokenEntity();
        token.setUser(user);
        return token;
    }

    public static VerificationTokenEntity updateLazyBy(TokenDto dto, VerificationTokenEntity token) {
        return isValid(token) ? token : updateBy(dto, token);
    }

    public static VerificationTokenEntity updateBy(TokenDto dto, VerificationTokenEntity token) {
        token.setExpiresAt(dto.expiresAt());
        token.setSecret(dto.secret());
        return token;
    }

    public static boolean isValid(AbstractTokenEntity token) {
        Instant now = Instant.now();
        Instant expiration = token.getExpiresAt();
        String secret = token.getSecret();

        boolean isErrorFound = token == null
                || token.getUser() == null
                || secret.isBlank()
                || secret.isEmpty()
                || expiration == null
                || expiration.isBefore(now);

        return !isErrorFound;

    }

}
