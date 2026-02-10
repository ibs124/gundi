package ibs124.gundi.model.application;

import java.time.Instant;
import java.util.Collection;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsDto implements UserDetails, CredentialsContainer {

    private Collection<? extends GrantedAuthority> authorities;
    private String username;
    private String password;
    private Long id;
    private String primaryEmail;
    private String fullName;
    private Boolean isEnabled;
    private Instant lockedAt;
    private Instant accountExpiresAt;

    @Override
    public boolean isEnabled() {
        return this.getIsEnabled();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.getAccountExpiresAt().isBefore(Instant.now());
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.getLockedAt() == null;
    }

    @Override
    public void eraseCredentials() {
        this.setPassword(null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String passowrd) {
        this.password = passowrd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String email) {
        this.primaryEmail = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Instant getLockedAt() {
        return lockedAt;
    }

    public void setLockedAt(Instant lockedAt) {
        this.lockedAt = lockedAt;
    }

    public Instant getAccountExpiresAt() {
        return accountExpiresAt;
    }

    public void setAccountExpiresAt(Instant accountExpiresAt) {
        this.accountExpiresAt = accountExpiresAt;
    }

}
