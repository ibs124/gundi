package ibs124.gundi.service.auth.domain;

import org.springframework.stereotype.Service;

import ibs124.gundi.exception.ResourceReadingException;
import ibs124.gundi.mapper.EmailMapper;
import ibs124.gundi.model.application.EmailCreateDTO;
import ibs124.gundi.model.application.EmailDTO;
import ibs124.gundi.model.domain.Email;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.repository.EmailRepository;
import ibs124.gundi.repository.UserRepository;

@Service
class EmailCreateDomainServiceImpl implements EmailCreateDomainService {

    private final EmailRepository emailRepository;
    private final UserRepository userRepository;
    private final EmailMapper emailMapper;

    public EmailCreateDomainServiceImpl(EmailRepository emailRepository, UserRepository userRepository,
            EmailMapper emailMapper) {
        this.emailRepository = emailRepository;
        this.userRepository = userRepository;
        this.emailMapper = emailMapper;
    }

    @Override
    public EmailDTO create(EmailCreateDTO dto) {
        User user = this.userRepository
                .findById(dto.userId())
                .orElseThrow(() -> new ResourceReadingException());

        Email email = this.emailMapper
                .mapToDomainModel(dto);

        email.setUser(user);

        email = this.emailRepository.save(email);

        return this.emailMapper.mapToApplicationModel(email);
    }

}