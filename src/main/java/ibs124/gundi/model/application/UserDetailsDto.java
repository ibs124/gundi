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
    private boolean isEnabled;
    private Instant lockedAt;
    private Instant accountExpiresAt;

    public UserDetailsDto() {
        super();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.getAccountExpiresAt() != null
                && this.getAccountExpiresAt().isAfter(Instant.now());
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.getLockedAt() == null;
    }

    @Override
    public void eraseCredentials() {
        this.setPassword(null);
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
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
