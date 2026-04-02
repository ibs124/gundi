package ibs124.gundi.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "verification_tokens")
public class VerificationTokenEntity extends AbstractTokenEntity {

    public VerificationTokenEntity() {
        super();
    }

    public VerificationTokenEntity(UserEntity user) {
        super();
        this.setUser(user);
    }

}
