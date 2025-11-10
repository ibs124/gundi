package ibs124.gundi.model.domain;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Entity
public class User extends AbstractAuditableDomainModel {

    private Set<UserRole> roles;
    private String username;
    private String password;
    private String fullName;
    private String email;

    public User() {
        super();
    }

    @ManyToMany
    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
