package ibs124.gundi.model.domain;

import ibs124.gundi.model.enumm.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Authority extends AbstractDomainModel {

    private Role name;

    public Authority() {
        super();
    }

    public Authority(Role x) {
        this();
        this.setName(x);
    }

    @Enumerated(EnumType.STRING)
    public Role getName() {
        return name;
    }

    public void setName(Role type) {
        this.name = type;
    }
}
