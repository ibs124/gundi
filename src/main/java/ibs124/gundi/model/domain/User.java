package ibs124.gundi.model.domain;

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
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;

@Entity
public class User extends AbstractAuditableDomainModel {

    private Set<Role> roles;
    private String fullName;
    private String username;
    private String password;
    private String primaryEmail;
    private Boolean isEnabled;
    private Instant lastVerifiedAt;
    private Instant accountExpiresAt;
    private Instant mfaEnabledAt;
    private Instant lockedAt;

    public User() {
        super();
        this.setRoles(new HashSet<>());
    }

    @Transient
    public boolean addRole(Role role) {
        return this.getRoles().add(role);
    }

    @Transient
    public boolean removeRole(Role role) {
        return this.getRoles().remove(role);
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
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

    @FutureOrPresent
    public Instant getMfaEnabledAt() {
        return mfaEnabledAt;
    }

    public void setMfaEnabledAt(Instant mfaEnabledAt) {
        this.mfaEnabledAt = mfaEnabledAt;
    }

    @FutureOrPresent
    public Instant getLockedAt() {
        return lockedAt;
    }

    public void setLockedAt(Instant lockedAt) {
        this.lockedAt = lockedAt;
    }

}
