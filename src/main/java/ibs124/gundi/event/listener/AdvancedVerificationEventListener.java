package ibs124.gundi.event.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import ibs124.gundi.common.email_sender.EmailSendRequest;
import ibs124.gundi.common.email_sender.EmailSender;
import ibs124.gundi.common.template_compiler.TemplateCompileRequest;
import ibs124.gundi.common.template_compiler.TemplateCompiler;
import ibs124.gundi.config.PropertyConfig;
import ibs124.gundi.constant.Env;
import ibs124.gundi.constant.Routes;
import ibs124.gundi.constant.Templates;
import ibs124.gundi.constant.ThymeleafEnv;
import ibs124.gundi.event.AbstractVerificationEvent;
import ibs124.gundi.event.UserPasswordResetEvent;
import ibs124.gundi.event.UserVerificationEvent;
import ibs124.gundi.model.config.VerificationEmailProperties;
import ibs124.gundi.model.config.VerificationProperties;
import ibs124.gundi.model.config.VerificationTokenProperties;

@Component
class AbstractVerificationEventListener {

    private final Logger logger;
    private final PropertyConfig config;
    private final EmailSender emailSender;
    private final TemplateCompiler templateCompiler;

    public AbstractVerificationEventListener(PropertyConfig config,
            EmailSender emailSender,
            TemplateCompiler templateCompiler) {
        this.config = config;
        this.emailSender = emailSender;
        this.templateCompiler = templateCompiler;

        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    @EventListener
    public void onVerification(UserVerificationEvent event) {
        this.sendEmail(
                event,
                config.verification(),
                Routes.VERIFICATION,
                Templates.VERIFICATION_EMAIL);
    }

    @EventListener
    public void onPasswordReset(UserPasswordResetEvent event) {
        this.sendEmail(
                event,
                config.passwordReset(),
                Routes.PASSWORD_RESET_VERIFY,
                Templates.PASSWORD_RESET_EMAIL);
    }

    private void sendEmail(
            AbstractVerificationEvent event,
            VerificationProperties config,
            String contextUrl,
            String emailTemplate) {

        VerificationTokenProperties tokenConfig = config.token();

        String token = this.getTokenByEventConfigAndContextUrl(
                event, tokenConfig, contextUrl);

        if (this.config.debug().verification()) {
            this.sendDebugByEventAndToken(event, token);
            return;
        }

        TemplateCompileRequest templateRequest = new TemplateCompileRequest(emailTemplate)
                .addVariable(ThymeleafEnv.URL, Env.GUNDI_LOGO_URL)
                .addVariable(ThymeleafEnv.TOKEN, token)
                .addVariable(ThymeleafEnv.EXPIRATION, tokenConfig.expirationMinutes());

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

    private void sendDebugByEventAndToken(AbstractVerificationEvent e, String t) {
        this.logger.info(
                "Verification sent: type = {} , secret = {} , email = {}",
                e.getClass().getName(),
                t,
                e.getEmail());
    }

    private String getTokenByEventConfigAndContextUrl(
            AbstractVerificationEvent event,
            VerificationTokenProperties config,
            String contextUrl) {

        if (!config.useLink()) {
            return event.getSecret();
        }

        String link = UriComponentsBuilder
                .fromUriString(event.getAppUrl())
                .path(contextUrl)
                .queryParam(Routes.VAR_TOKEN, event.getSecret())
                .build()
                .toUriString();

        return link;
    }

}