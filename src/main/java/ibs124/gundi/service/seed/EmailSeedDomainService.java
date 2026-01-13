package ibs124.gundi.service.seed;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import ibs124.gundi.config.SeedConfig;
import ibs124.gundi.model.domain.Email;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.repository.EmailRepository;

@Service
public class EmailSeedDomainService {

    private final EmailRepository emailRepository;

    public EmailSeedDomainService(EmailRepository emailRepository) {
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
        String name = user.getUsername().concat(SeedConfig.PRIMARY_EMAIL_SUFFIX);
        Email email = new Email();
        email.setUser(user);
        email.setName(name);
        email.setVerifiedAt(Instant.now());
        email.setPrimary(true);

        return email;
    }
}
