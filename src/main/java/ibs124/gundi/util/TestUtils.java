package ibs124.gundi.util;

import java.time.Instant;

import ibs124.gundi.event.AbstractVerificationEvent;
import ibs124.gundi.model.application.TokenDto;
import ibs124.gundi.model.persistence.AbstractToken;
import ibs124.gundi.model.persistence.User;
import ibs124.gundi.model.persistence.VerificationToken;

public abstract class TestUtils {

    public static void sendVerification(AbstractVerificationEvent e) {
        String message = "%n[Verification Sent] type = %s , secret = %s , email = %s%n"
                .formatted(e.getClass().getName(), e.getSecret(), e.getEmail());
        System.out.println(message);
    }

    public static final VerificationToken createBy(User user, TokenDto dto) {
        VerificationToken token = createBy(user);
        return updateBy(dto, token);
    }

    public static VerificationToken createBy(User user) {
        VerificationToken token = new VerificationToken();
        token.setUser(user);
        return token;
    }

    public static VerificationToken updateLazyBy(TokenDto dto, VerificationToken token) {
        return isValid(token) ? token : updateBy(dto, token);
    }

    public static VerificationToken updateBy(TokenDto dto, VerificationToken token) {
        token.setExpiresAt(dto.expiresAt());
        token.setSecret(dto.secret());
        return token;
    }

    public static boolean isValid(AbstractToken token) {
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
