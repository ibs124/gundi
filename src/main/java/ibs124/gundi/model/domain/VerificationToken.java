package ibs124.gundi.model.domain;

import java.time.Instant;

import jakarta.persistence.Entity;

@Entity
public class VerificationToken extends AbstractToken {

    public VerificationToken(User user, Instant expiresAt, String secret) {
        super(user, expiresAt, secret);
    }

}
