package ibs124.gundi.service.auth.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import ibs124.gundi.event.UserVerificationEvent;
import ibs124.gundi.exception.ResourceCreatingException;
import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.model.application.RegisterDTO;
import ibs124.gundi.model.application.RegisterResponseDTO;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.service.auth.RegistrationService;
import ibs124.gundi.service.auth.component.EmailCreator;
import ibs124.gundi.service.auth.component.UserCreator;
import ibs124.gundi.service.auth.component.VerificationTokenCreator;
import jakarta.transaction.Transactional;

@Service
class RegistrationServiceImpl implements RegistrationService {

    private final UserMapper userMapper;
    private final UserCreator userCreator;
    private final EmailCreator emailCreator;
    private final VerificationTokenCreator tokenCreator;
    private final ApplicationEventPublisher eventPublisher;

    public RegistrationServiceImpl(
            UserMapper userMapper,
            UserCreator userCreatingService,
            EmailCreator emailCreatingService,
            VerificationTokenCreator tokenCreatingService,
            ApplicationEventPublisher eventPublisher) {
        this.userMapper = userMapper;
        this.userCreator = userCreatingService;
        this.emailCreator = emailCreatingService;
        this.tokenCreator = tokenCreatingService;
        this.eventPublisher = eventPublisher;
    }

    @Override
    @Transactional
    public RegisterResponseDTO register(RegisterDTO request) {
        try {
            User user = this.userMapper
                    .mapToDomainModel(request.user());

            user = this.userCreator.create(user);

            String email = this.emailCreator
                    .createPrymaryEmailByUser(user, request.user().emailAddress())
                    .getEmailAddress();

            String verificationToken = this.tokenCreator
                    .createByUser(user)
                    .getValue();

            var event = new UserVerificationEvent(
                    verificationToken, email, request.appURL());

            this.eventPublisher
                    .publishEvent(event);

            RegisterResponseDTO response = new RegisterResponseDTO(
                    email, verificationToken);

            return response;
        } catch (Exception e) {
            throw new ResourceCreatingException(e);
        }
    }

}