package ibs124.gundi.model.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "email_verification_tokens")
public class EmailVerificationTokenEntity extends AbstractTokenEntity {

    private String sentTo;

    public EmailVerificationTokenEntity() {
        super();
    }

    @Column(nullable = false, unique = true)
    public String getSentTo() {
        return sentTo;
    }

    public void setSentTo(String emailRequest) {
        this.sentTo = emailRequest;
    }

}
