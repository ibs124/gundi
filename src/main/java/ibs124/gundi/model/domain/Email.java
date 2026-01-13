package ibs124.gundi.model.domain;

import java.time.Instant;

import ibs124.gundi.validation.constraint.ValidEmail;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "is_primary" }))
public class Email extends AbstractDomainModel {

    private User user;
    private String emailAddress;
    private Instant verifiedAt;
    private Boolean primary;

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
    public Instant getVerifiedAt() {
        return verifiedAt;
    }

    public void setVerifiedAt(Instant verifiedAt) {
        this.verifiedAt = verifiedAt;
    }

    @Column(name = "is_primary")
    public Boolean isPrimary() {
        return this.primary == null ? false : this.primary;
    }

    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }

}
