package ibs124.gundi.service.auth.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import ibs124.gundi.event.UserVerificationEvent;
import ibs124.gundi.exception.ResourceCreatingException;
import ibs124.gundi.model.application.EmailCreateDTO;
import ibs124.gundi.model.application.RegisterDTO;
import ibs124.gundi.model.application.RegisterResponseDTO;
import ibs124.gundi.service.auth.AuthTokenCreatingService;
import ibs124.gundi.service.auth.EmailCreatingService;
import ibs124.gundi.service.auth.RegistrationService;
import ibs124.gundi.service.auth.UserCreatingService;
import jakarta.transaction.Transactional;

@Service
class RegistrationServiceImpl implements RegistrationService {

    private final UserCreatingService userCreatingService;
    private final EmailCreatingService emailCreatingService;
    private final AuthTokenCreatingService tokenCreatingService;
    private final ApplicationEventPublisher eventPublisher;

    public RegistrationServiceImpl(
            UserCreatingService userCreatingService,
            EmailCreatingService emailCreatingService,
            AuthTokenCreatingService tokenCreatingService,
            ApplicationEventPublisher eventPublisher) {
        this.userCreatingService = userCreatingService;
        this.emailCreatingService = emailCreatingService;
        this.tokenCreatingService = tokenCreatingService;
        this.eventPublisher = eventPublisher;
    }

    @Override
    @Transactional
    public RegisterResponseDTO register(RegisterDTO request, String appUrl) {
        try {
            long userID = this.userCreatingService
                    .create(request)
                    .id();

            String email = this.emailCreatingService
                    .create(new EmailCreateDTO(userID, request.email(), true))
                    .name();

            String token = this.tokenCreatingService
                    .createByUserId(userID);

            this.eventPublisher
                    .publishEvent(new UserVerificationEvent(token, email, appUrl));

            RegisterResponseDTO response = new RegisterResponseDTO(email, token);

            return response;
        } catch (Exception e) {
            throw new ResourceCreatingException(e);
        }
    }

}