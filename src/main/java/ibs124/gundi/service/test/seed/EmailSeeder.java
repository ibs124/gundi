package ibs124.gundi.service.test.seed;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Component;

import ibs124.gundi.model.domain.Email;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.repository.EmailRepository;

@Component
public class EmailSeeder {

    private final EmailRepository emailRepository;

    public EmailSeeder(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public List<Email> seedPrimaryEmails(List<User> users) {
        List<Email> emails = users
                .stream()
                .map(x -> this.createPrimaryEmailByUser(x))
                .toList();

        return this.emailRepository.saveAll(emails);
    }

    private Email createPrimaryEmailByUser(User user) {
        String emailAddress = user
                .getUsername().concat(Config.PRIMARY_EMAIL_SUFFIX);

        Email email = new Email(user, emailAddress);

        email.setLastVerifiedAt(Instant.now());

        return email;
    }
}
