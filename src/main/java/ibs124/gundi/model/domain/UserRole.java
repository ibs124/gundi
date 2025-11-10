package ibs124.gundi.model.domain;

import ibs124.gundi.model.enumm.UserRoleType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class UserRole extends AbstractAuditableDomainModel {

    private UserRoleType type;

    public UserRole() {
        super();
    }

    @Enumerated(EnumType.STRING)
    public UserRoleType getType() {
        return type;
    }

    public void setType(UserRoleType type) {
        this.type = type;
    }
}
