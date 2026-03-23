package ibs124.gundi.model.entity;

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
public class AbstractTokenEntity extends AbstractEntity {

    private UserEntity user;
    private Instant expiresAt;
    private String secret;

    public AbstractTokenEntity() {
        super();
    }

    @Valid
    @MapsId
    @OneToOne
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
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
