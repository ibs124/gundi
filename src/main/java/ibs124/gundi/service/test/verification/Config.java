package ibs124.gundi.service.test.verification;

abstract class Config {

    public static final String KEY_CSS_INLINE = "inlineCss";

    public static final String KEY_VERIFICATION_TOKEN = "verificationToken";

    public static final String EMAIL_FROM = "noreply@gundi.com";

    public static final String EMAIL_DISPLAY_NAME = "Gundi";

    public static final String EMAIL_TO = "ibs124experimental@gmail.com";

    public static final String EMAIL_SUBJECT = "Confirm your email address";

    public static final String VERIFICATION_CODE = "094532";

    public static final String PATH_HTML = "/emails/new-user-verification";

    public static final String PATH_CSS = "classpath:static//css/emails.css";

}
