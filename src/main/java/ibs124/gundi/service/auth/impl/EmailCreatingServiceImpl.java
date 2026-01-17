package ibs124.gundi.service.auth.impl;

import org.springframework.stereotype.Service;

import ibs124.gundi.model.application.EmailCreateDto;
import ibs124.gundi.model.domain.Email;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.repository.EmailRepository;
import ibs124.gundi.repository.UserRepository;
import ibs124.gundi.service.auth.EmailCreatingService;

@Service
class EmailCreatingServiceImpl implements EmailCreatingService {

    private final EmailRepository emailRepository;
    private final UserRepository userRepository;

    public EmailCreatingServiceImpl(
            EmailRepository emailRepository,
            UserRepository userRepository) {
        this.emailRepository = emailRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Long createPrimaryEmail(EmailCreateDto request) {
        User userRef = this.userRepository
                .getReferenceById(request.userId());

        Email email = new Email(userRef, request.emailAddress());

        email.setPrimary(true);

        email = this.emailRepository.save(email);

        return email.getId();
    }

}
