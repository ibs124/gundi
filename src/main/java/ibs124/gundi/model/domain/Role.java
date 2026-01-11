package ibs124.gundi.model.domain;

import ibs124.gundi.model.enumm.RoleName;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Role extends AbstractDomainModel {

    private RoleName name;

    public Role() {
        super();
    }

    public Role(RoleName x) {
        this();
        this.setName(x);
    }

    @Enumerated(EnumType.STRING)
    public RoleName getName() {
        return name;
    }

    public void setName(RoleName type) {
        this.name = type;
    }
}
