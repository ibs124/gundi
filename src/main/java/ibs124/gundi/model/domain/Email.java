package ibs124.gundi.model.domain;

import java.time.Instant;

import ibs124.gundi.validation.constraint.ValidEmail;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.PastOrPresent;

@Entity
public class Email extends AbstractDomainModel {

    private User user;
    private String name;
    private Instant lastVerifiedAt;

    public Email() {
        super();
    }

    public Email(User user, String name) {
        this();
        this.setUser(user);
        this.setName(name);
    }

    @ManyToOne(optional = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ValidEmail
    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PastOrPresent
    public Instant getLastVerifiedAt() {
        return lastVerifiedAt;
    }

    public void setLastVerifiedAt(Instant verifiedAt) {
        this.lastVerifiedAt = verifiedAt;
    }
}
