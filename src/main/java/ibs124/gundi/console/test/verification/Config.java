package ibs124.gundi.console.test.verification;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import ibs124.gundi.constant.Constants;

abstract class Config {

    public static final String[] MAIL_TO = { "ibs124experimental@gmail.com" };

    public static final String MAIL_SENT_MESSAGE = "Verification email sent, check inboxes at: "
            + Arrays.stream(MAIL_TO).collect(Collectors.joining(", "));;

    public static final String TEMPLATE = "/emails/new-user-verification";

    public static final Map<String, Object> TEMPALTE_ATTRIBUTES = Map.of(
        "logoUrl", new String(Constants.GUNDI_LOGO_URL),
        "verificationToken", new String("004591")
    );

}
