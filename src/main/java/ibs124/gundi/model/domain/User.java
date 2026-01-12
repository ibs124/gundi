package ibs124.gundi.model.domain;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import ibs124.gundi.validation.constraint.ValidEmail;
import ibs124.gundi.validation.constraint.ValidPassword;
import ibs124.gundi.validation.constraint.ValidUsername;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class User extends AbstractAuditableDomainModel {

    private Map<String, Email> emails;
    private Set<Role> roles;
    private String username;
    private String password;
    private String fullName;
    private String primaryEmail;
    private Boolean isEnabled;
    private Instant lastVerifiedAt;

    public User() {
        super();
        this.setEmails(new LinkedHashMap<>());
        this.setRoles(new LinkedHashSet<>());
    }

    @Transient
    public boolean add(Role x) {
        return this.getRoles().add(x);
    }

    @Transient
    public boolean remove(Role x) {
        return this.getRoles().remove(x);
    }

    @Transient
    public Email addEmail(String email) {
        return this.getEmails()
                .put(email, new Email(this, email));
    }

    @Transient
    public Email removeEmail(String email) {
        return this.getEmails().remove(email);
    }

    @Transient
    public Email getEmail(String email) {
        return this.getEmails().get(email);
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "name")
    public Map<String, Email> getEmails() {
        return emails;
    }

    public void setEmails(Map<String, Email> emails) {
        this.emails = emails;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @ValidPassword
    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setIsEnabled(Boolean enabled) {
        this.isEnabled = enabled;
    }

    public Instant getLastVerifiedAt() {
        return lastVerifiedAt;
    }

    public void setLastVerifiedAt(Instant lastVerifiedAt) {
        this.lastVerifiedAt = lastVerifiedAt;
    }

}
