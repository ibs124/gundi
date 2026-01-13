package ibs124.gundi.service.auth.application;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import ibs124.gundi.event.UserVerificationEvent;
import ibs124.gundi.exception.ResourceCreatingException;
import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.model.application.RegisterDTO;
import ibs124.gundi.model.application.RegisterResponseDTO;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.service.auth.domain.EmailCreateDomainService;
import ibs124.gundi.service.auth.domain.TokenCreateDomainService;
import ibs124.gundi.service.auth.domain.UserCreateDomainService;
import jakarta.transaction.Transactional;

@Service
class RegistrationServiceImpl implements RegistrationService {

    private final UserMapper userMapper;
    private final UserCreateDomainService userCreatingService;
    private final EmailCreateDomainService emailCreatingService;
    private final TokenCreateDomainService tokenCreatingService;
    private final ApplicationEventPublisher eventPublisher;

    public RegistrationServiceImpl(UserMapper userMapper, UserCreateDomainService userCreatingService,
            EmailCreateDomainService emailCreatingService, TokenCreateDomainService tokenCreatingService,
            ApplicationEventPublisher eventPublisher) {
        this.userMapper = userMapper;
        this.userCreatingService = userCreatingService;
        this.emailCreatingService = emailCreatingService;
        this.tokenCreatingService = tokenCreatingService;
        this.eventPublisher = eventPublisher;
    }

    @Override
    @Transactional
    public RegisterResponseDTO register(RegisterDTO request, String appUrl) {
        try {
            User user = this.userCreatingService
                    .create(this.userMapper.mapToDomainModel(request));

            String email = this.emailCreatingService
                    .createPrymaryEmailByUser(user, request.emailAddress())
                    .getEmailAddress();

            String verificationToken = this.tokenCreatingService
                    .createByUser(user)
                    .getValue();

            var event = new UserVerificationEvent(verificationToken, email, appUrl);

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