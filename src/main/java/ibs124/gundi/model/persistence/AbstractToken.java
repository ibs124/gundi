package ibs124.gundi.model.persistence;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@MappedSuperclass
public class AbstractToken extends AbstractDomainModel {

    private User user;
    private Instant expiresAt;
    private String secret;

    public AbstractToken() {
        super();
    }

    @Valid
    @MapsId
    @OneToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Future
    @NotNull
    @Column(nullable = false)
    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    @NotBlank
    @Column(nullable = false, unique = true)
    public String getSecret() {
        return secret;
    }

    public void setSecret(String value) {
        this.secret = value;
    }

}
