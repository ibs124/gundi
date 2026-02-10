package ibs124.gundi.console.test;

import org.springframework.stereotype.Component;

@Component
public class VerificationCommandRunner {

    private static final String EMAIL = "ibs124experimental@gmail.com";

    private static final String MESSAGE = "Verification email sent, check inboxes at: " +
            EMAIL;

    private static final String OTP = "004591";

    // public VerificationCommandRunner(
    // VerificationSendingService verificationSendingService) {
    // this.verificationSendingService = verificationSendingService;
    // }

    // @Override
    // public String run(String... args) {
    // var request = new VerificationSendDto(EMAIL, OTP, null);

    // this.verificationSendingService.sendVerification(request);

    // return MESSAGE;
    // }

}
