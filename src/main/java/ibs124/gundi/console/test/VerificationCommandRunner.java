package ibs124.gundi.console.test;

import org.springframework.stereotype.Component;

import ibs124.gundi.console.CommandRunner;

import ibs124.gundi.model.application.VerificationSendDto;
import ibs124.gundi.model.enumm.VerificationType;
import ibs124.gundi.service.auth.VerificationSendingService;

@Component
public class VerificationCommandRunner implements CommandRunner {

    private static final String EMAIL = "ibs124experimental@gmail.com";

    private static final String MESSAGE = "Verification email sent, check inboxes at: " +
            EMAIL;

    private static final String OTP = "004591";

    private final VerificationSendingService verificationSendingService;

    public VerificationCommandRunner(
            VerificationSendingService verificationSendingService) {
        this.verificationSendingService = verificationSendingService;
    }

    @Override
    public String run(String... args) {

        VerificationSendDto request = new VerificationSendDto(
                VerificationType.NEW_USER,
                EMAIL,
                OTP,
                null);

        this.verificationSendingService.sendVerification(request);

        return MESSAGE;
    }

}
