package ibs124.gundi.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "password_reset_tokens")
public class PasswordResetTokenEntity extends AbstractTokenEntity {

}
