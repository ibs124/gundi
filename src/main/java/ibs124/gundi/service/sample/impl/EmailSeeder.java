package ibs124.gundi.service.sample.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ibs124.gundi.model.persistence.EmailEntity;
import ibs124.gundi.model.persistence.UserEntity;
import ibs124.gundi.repository.EmailRepository;

@Component
public class EmailSeeder {

    private final EmailRepository emailRepository;

    public EmailSeeder(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public List<EmailEntity> seedPrimaryEmails(List<UserEntity> users) {
        if (this.emailRepository.count() > 0) {
            return new ArrayList<>();
        }
        
        List<EmailEntity> emails = users
                .stream()
                .map(x -> this.createPrimaryEmailByUser(x))
                .toList();

        return this.emailRepository.saveAll(emails);
    }

    private EmailEntity createPrimaryEmailByUser(UserEntity user) {
        String emailAddress = user
                .getUsername().concat(Config.PRIMARY_EMAIL_SUFFIX);

        EmailEntity email = new EmailEntity(user, emailAddress);

        email.setLastVerifiedAt(Instant.now());

        return email;
    }
}
