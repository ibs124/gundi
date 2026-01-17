package ibs124.gundi.service.auth.verification;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import ibs124.gundi.event.UserVerificationEvent;
import ibs124.gundi.model.application.VerificationSendDto;
import jakarta.transaction.Transactional;

@Service
class VerificationSendingServiceImpl implements VerificationSendingService {

    private final ApplicationEventPublisher eventPublisher;
    private final VerificationTokenCreatingService verificationTokenCreatingService;

    public VerificationSendingServiceImpl(
            ApplicationEventPublisher eventPublisher,
            VerificationTokenCreatingService verificationTokenCreatingService) {
        this.eventPublisher = eventPublisher;
        this.verificationTokenCreatingService = verificationTokenCreatingService;
    }

    @Override
    @Transactional
    public void sendNewUserVerification(VerificationSendDto request) {
        String token = this.verificationTokenCreatingService
                .createNewUserVerificationTokenByUserId(request.userId());

        UserVerificationEvent event = new UserVerificationEvent(
                token, request.email(), request.appUrl());

        this.eventPublisher.publishEvent(event);
    }

}