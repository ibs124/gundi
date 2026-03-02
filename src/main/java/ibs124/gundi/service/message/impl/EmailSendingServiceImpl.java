package ibs124.gundi.service.message.impl;

import java.io.UnsupportedEncodingException;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import ibs124.gundi.model.application.EmailSendDto;
import ibs124.gundi.service.message.EmailSendingService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
class EmailSendingServiceImpl implements EmailSendingService {

    private final JavaMailSender javaMailSender;

    public EmailSendingServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(EmailSendDto dto) {
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