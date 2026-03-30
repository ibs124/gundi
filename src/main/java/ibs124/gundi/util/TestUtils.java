package ibs124.gundi.util;

import ibs124.gundi.event.AbstractVerificationEvent;

public abstract class TestUtils {

    public static void sendVerification(AbstractVerificationEvent e) {
        String message = "%n[Verification Sent] type = %s , secret = %s , email = %s%n"
                .formatted(e.getClass().getName(), e.getSecret(), e.getEmail());
        System.out.println(message);
    }

}
