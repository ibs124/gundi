package ibs124.gundi.event.listener;

import static ibs124.gundi.constant.Templates.AUTH_VERIFICATION_EMAIL;

import static ibs124.gundi.constant.Env.GUNDI_LOGO_URL;
import static ibs124.gundi.constant.ThymeleafEnv.TOKEN;
import static ibs124.gundi.constant.ThymeleafEnv.EXPIRATION;
import static ibs124.gundi.constant.ThymeleafEnv.URL;

import ibs124.gundi.model.config.VerificationEmailProperties;
import ibs124.gundi.model.config.VerificationProperties;
import ibs124.gundi.model.config.VerificationTokenProperties;
import ibs124.gundi.util.TestUtils;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import ibs124.gundi.common.email_sender.EmailSendRequest;
import ibs124.gundi.common.email_sender.EmailSender;
import ibs124.gundi.common.template_compiler.TemplateCompileRequest;
import ibs124.gundi.common.template_compiler.TemplateCompiler;
import ibs124.gundi.config.PropertyConfig;
import ibs124.gundi.event.UserVerificationEvent;

@Component
class UserVerificationEventListener
        implements ApplicationListener<UserVerificationEvent> {

    private final PropertyConfig config;
    private final EmailSender emailSender;
    private final TemplateCompiler templateCompiler;

    public UserVerificationEventListener(PropertyConfig config, EmailSender emailSender,
            TemplateCompiler templateCompiler) {
        this.config = config;
        this.emailSender = emailSender;
        this.templateCompiler = templateCompiler;
    }

    @Override
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onApplicationEvent(UserVerificationEvent event) {

        VerificationProperties config = this.config.verification();

        VerificationTokenProperties tokenConfig = config.token();

        if (this.config.debug().verification()) {
            TestUtils.sendVerification(event);
            return;
        }

        TemplateCompileRequest templateRequest = new TemplateCompileRequest(
                AUTH_VERIFICATION_EMAIL)
                .addVariable(URL, GUNDI_LOGO_URL)
                .addVariable(TOKEN, event.getSecret())
                .addVariable(EXPIRATION, tokenConfig.expirationMinutes());

        String message = this.templateCompiler.compileHtml(templateRequest);

        VerificationEmailProperties mailConfig = config.mail();

        EmailSendRequest emailRequest = EmailSendRequest
                .builder()
                .from(mailConfig.from())
                .displayName(mailConfig.displayName())
                .to(event.getEmail())
                .subject(mailConfig.subject())
                .text(message)
                .isHtml(mailConfig.isHtml())
                .build();

        this.emailSender.sendEmail(emailRequest);

    }

}
