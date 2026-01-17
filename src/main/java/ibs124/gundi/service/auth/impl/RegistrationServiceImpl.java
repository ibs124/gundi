package ibs124.gundi.service.auth.impl;

import org.springframework.stereotype.Service;

import ibs124.gundi.exception.ResourceCreatingException;
import ibs124.gundi.model.application.EmailCreateDto;
import ibs124.gundi.model.application.RegisterDto;
import ibs124.gundi.model.application.VerificationSendDto;
import ibs124.gundi.service.auth.EmailCreatingService;
import ibs124.gundi.service.auth.RegistrationService;
import ibs124.gundi.service.auth.UserCreatingService;
import ibs124.gundi.service.auth.VerificationSendingService;
import jakarta.transaction.Transactional;

@Service
class RegistrationServiceImpl implements RegistrationService {

    private final UserCreatingService userCreatingService;
    private final EmailCreatingService emailCreatingService;
    private final VerificationSendingService verificationSendingService;

    public RegistrationServiceImpl(
            UserCreatingService userService,
            EmailCreatingService emailCreatingService,
            VerificationSendingService verificationService) {
        this.userCreatingService = userService;
        this.emailCreatingService = emailCreatingService;
        this.verificationSendingService = verificationService;
    }

    @Override
    @Transactional
    public Long registerUser(RegisterDto request) {
        try {
            Long userId = this.userCreatingService
                    .createUser(request.user());

            String email = request.user().primaryEmail();

            this.emailCreatingService
                    .createPrimaryEmail(new EmailCreateDto(userId, email));

            this.verificationSendingService
                    .sendNewUserVerification(
                            new VerificationSendDto(email, request.appUrl(), userId));

            return userId;
        } catch (Exception e) {
            throw new ResourceCreatingException(e);
        }
    }
}