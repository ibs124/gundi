package ibs124.gundi.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import ibs124.gundi.event.UserVerificationEvent;

@Component
class UserVerificationEventListener
        implements ApplicationListener<UserVerificationEvent> {

    @Override
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onApplicationEvent(@NonNull UserVerificationEvent event) {
        // TODO Auto-generated method stub

    }

}
