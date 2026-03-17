package ibs124.gundi.model.persistence;

import java.time.Instant;

import ibs124.gundi.model.persistence.id.UserAuthorityId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@IdClass(UserAuthorityId.class)
@Table(name = "user_authorities")
public class UserAuthorityEntity {

    private UserEntity user;
    private AuthorityEntity authority;
    private Instant issedAt;

    public UserAuthorityEntity() {
        super();
    }

    public UserAuthorityEntity(UserEntity user, AuthorityEntity authority) {
        this();
        this.setUser(user);
        this.setAuthority(authority);
        this.setIssedAt(null);
    }

    @Id
    @ManyToOne(optional = false)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Id
    @ManyToOne(optional = false)
    public AuthorityEntity getAuthority() {
        return authority;
    }

    public void setAuthority(AuthorityEntity authority) {
        this.authority = authority;
    }

    @Column(nullable = false)
    public Instant getIssedAt() {
        return issedAt;
    }

    public void setIssedAt(Instant issedAt) {
        if (issedAt == null) {
            issedAt = Instant.now();
        }
        this.issedAt = issedAt;
    }

}
