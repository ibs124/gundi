package ibs124.gundi.common.email_sender;

import java.io.UnsupportedEncodingException;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

class EmailSenderImpl implements EmailSender {

    private final JavaMailSender javaMailSender;

    public EmailSenderImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(EmailSendRequest dto) {
        MimeMessage message = this.javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom(dto.from(), dto.displayName());
            helper.setTo(dto.to());
            helper.setSubject(dto.subject());
            helper.setText(dto.text(), dto.isHtml());

            this.javaMailSender.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            System.err.println(e.getMessage());
        }
    }

}