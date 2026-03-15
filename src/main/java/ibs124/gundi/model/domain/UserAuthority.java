package ibs124.gundi.model.domain;

import java.time.Instant;

import ibs124.gundi.model.domain.id.UserAuthorityId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;

@Entity
@IdClass(UserAuthorityId.class)
public class UserAuthority {

    private User user;
    private Authority authority;
    private Instant issedAt;

    public UserAuthority() {
        super();
    }

    public UserAuthority(User user, Authority authority) {
        this();
        this.setUser(user);
        this.setAuthority(authority);
        this.setIssedAt(null);
    }

    @Id
    @ManyToOne(optional = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Id
    @ManyToOne(optional = false)
    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
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
