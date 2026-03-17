package ibs124.gundi.model.persistence;

import java.beans.Transient;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import ibs124.gundi.validation.constraint.ValidEmail;
import ibs124.gundi.validation.constraint.ValidPassword;
import ibs124.gundi.validation.constraint.ValidUsername;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "users")
public class UserEntity extends AbstractAuditableEntity {

    private Set<AuthorityEntity> authorities;
    private String fullName;
    private String username;
    private String password;
    private String primaryEmail;
    private boolean isEnabled;
    private Instant lastVerifiedAt;
    private Instant accountExpiresAt;
    private Instant mfaEnabledAt;
    private Instant lockedAt;

    public UserEntity() {
        super();
        this.setAuthorities(new HashSet<>());
    }

    @Transient
    public boolean addAuthority(AuthorityEntity role) {
        return this.getAuthorities().add(role);
    }

    @Transient
    public boolean removeAuthority(AuthorityEntity role) {
        return this.getAuthorities().remove(role);
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<AuthorityEntity> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<AuthorityEntity> roles) {
        if (roles == null) {
            roles = new HashSet<>();
        }

        this.authorities = roles;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @ValidUsername
    @Column(nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @ValidPassword
    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ValidEmail
    @Column(nullable = false, unique = true)
    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }

    @PastOrPresent
    public Instant getLastVerifiedAt() {
        return lastVerifiedAt;
    }

    public void setLastVerifiedAt(Instant lastVerifiedAt) {
        this.lastVerifiedAt = lastVerifiedAt;
    }

    @Future
    public Instant getAccountExpiresAt() {
        return accountExpiresAt;
    }

    public void setAccountExpiresAt(Instant accountExpiresAt) {
        this.accountExpiresAt = accountExpiresAt;
    }

    @PastOrPresent
    public Instant getMfaEnabledAt() {
        return mfaEnabledAt;
    }

    public void setMfaEnabledAt(Instant mfaEnabledAt) {
        this.mfaEnabledAt = mfaEnabledAt;
    }

    @PastOrPresent
    public Instant getLockedAt() {
        return lockedAt;
    }

    public void setLockedAt(Instant lockedAt) {
        this.lockedAt = lockedAt;
    }

}
