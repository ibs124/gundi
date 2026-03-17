package ibs124.gundi.model.persistence;

import java.time.Instant;

import ibs124.gundi.validation.constraint.ValidEmail;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.PastOrPresent;

@Entity
public class Email extends AbstractDomainModel {

    private User user;
    private String emailAddress;
    private Instant lastVerifiedAt;

    public Email() {
        super();
    }

    public Email(User user, String emailAddress) {
        this();
        this.setUser(user);
        this.setEmailAddress(emailAddress);
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
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String name) {
        this.emailAddress = name;
    }

    @PastOrPresent
    public Instant getLastVerifiedAt() {
        return lastVerifiedAt;
    }

    public void setLastVerifiedAt(Instant verifiedAt) {
        this.lastVerifiedAt = verifiedAt;
    }

}
