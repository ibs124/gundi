package ibs124.gundi.service.auth.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import ibs124.gundi.event.UserVerificationEvent;
import ibs124.gundi.exception.ResourceCreatingException;
import ibs124.gundi.model.dto.RegisterResponseDTO;
import ibs124.gundi.model.dto.UserCreateDTO;
import ibs124.gundi.model.dto.UserDTO;
import ibs124.gundi.service.auth.AuthTokenCreatingService;
import ibs124.gundi.service.auth.RegistrationService;
import ibs124.gundi.service.auth.UserCreatingService;
import jakarta.transaction.Transactional;

@Service
class RegistrationServiceImpl implements RegistrationService {

    private final UserCreatingService userCreatingService;
    private final AuthTokenCreatingService tokenCreatingService;
    private final ApplicationEventPublisher eventPublisher;

    public RegistrationServiceImpl(UserCreatingService userCreatingService,
            AuthTokenCreatingService tokenCreatingService, ApplicationEventPublisher eventPublisher) {
        this.userCreatingService = userCreatingService;
        this.tokenCreatingService = tokenCreatingService;
        this.eventPublisher = eventPublisher;
    }

    @Override
    @Transactional
    public RegisterResponseDTO register(UserCreateDTO request, String appUrl) {
        try {
            UserDTO user = this.userCreatingService
                    .create(request);

            String token = this.tokenCreatingService
                    .createByUserId(user.id());

            this.eventPublisher
                    .publishEvent(new UserVerificationEvent(token, user.primaryEmail(), appUrl));

            RegisterResponseDTO response = new RegisterResponseDTO(user.primaryEmail(), token);

            return response;
        } catch (Exception e) {
            throw new ResourceCreatingException(e);
        }
    }

}