package ibs124.gundi.model.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "verification_tokens")
public class VerificationTokenEntity extends AbstractTokenEntity {

}
