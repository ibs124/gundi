package ibs124.gundi.console.test.verification;

import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class Config {

    public static final String[] EMAIL_TO = { "ibs124experimental@gmail.com" };

    public static final String MESSAGE = "Verification email sent, check inboxes at: " +
            Arrays.stream(EMAIL_TO).collect(Collectors.joining(", "));

    public static final String OTP = "004591";
}
