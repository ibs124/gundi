package ibs124.gundi.common.email_sender;

import org.springframework.mail.javamail.JavaMailSender;

public abstract class EmailSenderFactory {

    public static EmailSender build(JavaMailSender mailSender) {
        return new EmailSenderImpl(mailSender);
    }
}
