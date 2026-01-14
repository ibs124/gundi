package ibs124.gundi.service.auth.component;

import org.springframework.stereotype.Service;

import ibs124.gundi.model.domain.Email;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.repository.EmailRepository;

@Service
class EmailCreatorImpl implements EmailCreator {

    private final EmailRepository emailRepository;

    public EmailCreatorImpl(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @Override
    public Email createPrymaryEmailByUser(User user, String emailAddress) {
        Email email = new Email(user, emailAddress);
        email.setPrimary(true);
        return this.emailRepository.save(email);
    }

}