package ibs124.gundi.service.auth.domain;

import org.springframework.stereotype.Service;

import ibs124.gundi.model.domain.Email;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.repository.EmailRepository;

@Service
class EmailCreateDomainServiceImpl implements EmailCreateDomainService {

    private final EmailRepository emailRepository;

    public EmailCreateDomainServiceImpl(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @Override
    public Email createPrymaryEmailByUser(User user, String emailAddress) {
        Email email = new Email(user, emailAddress);
        return this.emailRepository.save(email);
    }

}