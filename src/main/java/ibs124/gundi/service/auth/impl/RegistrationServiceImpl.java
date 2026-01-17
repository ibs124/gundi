package ibs124.gundi.service.auth.impl;

import org.springframework.stereotype.Service;

import ibs124.gundi.exception.ResourceCreatingException;
import ibs124.gundi.model.application.RegisterDto;
import ibs124.gundi.model.application.VerificationSendDto;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.service.auth.RegistrationService;
import ibs124.gundi.service.auth.UserCreatingService;
import ibs124.gundi.service.auth.verification.VerificationSendingService;
import jakarta.transaction.Transactional;

@Service
class RegistrationServiceImpl implements RegistrationService {

    private final UserCreatingService userCreatingService;
    private final VerificationSendingService verificationSendingService;

    public RegistrationServiceImpl(
            UserCreatingService userCreatingService,
            VerificationSendingService verificationSendingService) {
        this.userCreatingService = userCreatingService;
        this.verificationSendingService = verificationSendingService;
    }

    @Override
    @Transactional
    public Long registerUser(RegisterDto request) {
        try {
            Long userId = this.userCreatingService
                    .createUser(request.user());

            String email = request.user().primaryEmail();

            this.verificationSendingService
                    .sendNewUserVerification(
                            new VerificationSendDto(email, request.appUrl(), userId));

            return userId;
        } catch (Exception e) {
            String message = "Unexpected error occured while creating "
                    + User.class.toString();

            ResourceCreatingException error = new ResourceCreatingException(message, e);
            error.setTarget(User.class);

            throw error;

        }
    }
}