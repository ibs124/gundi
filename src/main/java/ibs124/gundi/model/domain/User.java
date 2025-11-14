package ibs124.gundi.model.domain;

import java.util.HashSet;
import java.util.Set;

import ibs124.gundi.validation.constraint.ValidEmail;
import ibs124.gundi.validation.constraint.ValidPassword;
import ibs124.gundi.validation.constraint.ValidUsername;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "users")
public class User extends AbstractAuditableDomainModel {

    private Set<UserRole> roles = new HashSet<>();
    private String username;
    private String password;
    private String email;
    private String fullName;

    public User() {
        super();
    }

    @Transient
    public UserRole removeRole(UserRole role) {
        this.getRoles().remove(role);
        return role;
    }

    @Transient
    public UserRole addRole(UserRole role) {
        this.getRoles().add(role);
        return role;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    @ValidEmail
    @Column(nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

}
