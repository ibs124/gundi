package ibs124.gundi.util;

import ibs124.gundi.model.application.TokenDto;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.domain.VerificationToken;

public abstract class TokenUtils {

    public static final VerificationToken createBy(User user, TokenDto dto) {
        VerificationToken token = createBy(user);
        return updateBy(dto, token);
    }

    public static VerificationToken createBy(User user) {
        VerificationToken token = new VerificationToken();
        token.setUser(user);
        return token;
    }

    public static VerificationToken updateBy(TokenDto dto, VerificationToken token) {
        token.setExpiresAt(dto.expiresAt());
        token.setSecret(dto.secret());
        return token;
    }

}
