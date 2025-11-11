package ibs124.gundi.model.domain;

import ibs124.gundi.model.enumm.UserRoleType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRole extends AbstractAuditableDomainModel {

    private UserRoleType type;

    public UserRole() {
        super();
    }

    public UserRole(UserRoleType x) {
        this();
        this.setType(x);
    }

    @Enumerated(EnumType.STRING)
    public UserRoleType getType() {
        return type;
    }

    public void setType(UserRoleType type) {
        this.type = type;
    }
}
