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
    private String name;
    private Instant verifiedAt;
    private Boolean primary;

    public Email() {
        super();
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
