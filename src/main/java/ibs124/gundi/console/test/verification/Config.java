package ibs124.gundi.console.test.verification;

abstract class Config {

    // Email

    public static final String EMAIL_FROM = "noreply@gundi.com";

    public static final String EMAIL_DISPLAY_NAME = "Gundi";

    public static final String[] EMAIL_TO = { "ibs124experimental@gmail.com" };

    public static final String EMAIL_SUBJECT = "Confirm your email address";

    public static final String VERIFICATION_CODE = "094532";

    // Keys

    public static final String KEY_CSS_INLINE = "inlineCss";

    public static final String KEY_VERIFICATION_TOKEN = "verificationToken";

    public static final String KEY_URL_LOGO = "logoUrl";

    // URLs

    public static final String URL_HTML = "/emails/new-user-verification";

    public static final String URL_CSS = "static/css/emails.css";

    public static final String URL_LOGO = "https://gitlab.com/ibs124/gundi/-/raw/feature/assets/logo-navy.png?ref_type=heads";

}
