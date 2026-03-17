package ibs124.gundi.model.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class EmailVerificationToken extends AbstractToken {

    private String sentTo;

    public EmailVerificationToken() {
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
