package ibs124.gundi.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import ibs124.gundi.event.UserVerificationEvent;

import ibs124.gundi.service.utility.VerificationSendingService;

@Component
class UserVerificationEventListener
        implements ApplicationListener<UserVerificationEvent> {

    private final VerificationSendingService verificationSendingService;

    public UserVerificationEventListener(
            VerificationSendingService verificationSendingService) {
        this.verificationSendingService = verificationSendingService;
    }

    @Override
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onApplicationEvent(@NonNull UserVerificationEvent event) {
        this.verificationSendingService
                .sendVerification(event.getVerificationRequest());

    }

}
