package ibs124.gundi.service.sample.impl;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Component;

import ibs124.gundi.model.entity.EmailEntity;
import ibs124.gundi.model.entity.UserEntity;
import ibs124.gundi.repository.EmailRepository;

@Component
public class EmailSeeder {

    private final EmailRepository emailRepository;

    public EmailSeeder(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public List<EmailEntity> seedPrimaryEmails(List<UserEntity> users) {
        List<EmailEntity> emails = users
                .stream()
                .map(x -> this.seedPrimaryEmailByUser(x))
                .toList();

        return this.emailRepository.saveAll(emails);
    }

    private EmailEntity seedPrimaryEmailByUser(UserEntity user) {
        EmailEntity email = new EmailEntity(user, user.getPrimaryEmail());

        email.setLastVerifiedAt(Instant.now());

        return email;
    }
}
