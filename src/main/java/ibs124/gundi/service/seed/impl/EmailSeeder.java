package ibs124.gundi.service.seed.impl;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

import ibs124.gundi.model.domain.Email;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.repository.EmailRepository;

@Component
class EmailSeeder {

    private final EmailRepository emailRepository;

    public EmailSeeder(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public List<Email> seedPrimaryEmails(
            List<User> users, JsonNode node) {
        String suffix = node.at("/emailSuffix").asText();

        List<Email> emails = users
                .stream()
                .map(x -> this.createPrimaryEmailByUserAndSuffixAndVerifiedAt(x, suffix))
                .toList();

        return this.emailRepository.saveAll(emails);
    }

    private Email createPrimaryEmailByUserAndSuffixAndVerifiedAt(
            User user, String suffix) {

        String name = user.getUsername().concat(suffix);
        Email email = new Email();
        email.setUser(user);
        email.setName(name);
        email.setVerifiedAt(Instant.now());
        email.setPrimary(true);

        return email;
    }
}
