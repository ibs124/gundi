package ibs124.gundi.util;

import java.time.Instant;

import ibs124.gundi.event.AbstractVerificationEvent;
import ibs124.gundi.model.application.TokenDto;
import ibs124.gundi.model.domain.AbstractToken;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.domain.VerificationToken;

public abstract class TestUtils {

    public static void sendVerification(AbstractVerificationEvent event) {
        System.out.println("\n [Verification Sent] " + getCustomToString(event) + "\n");
    }

    public static String getCustomToString(AbstractVerificationEvent event) {
        return String.format("%s[email=%s, secret=%s, appUrl=%s]",
                event.getClass().getName(),
                event.getEmail(),
                event.getSecret(),
                event.getAppUrl());
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
