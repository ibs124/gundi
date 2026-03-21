package ibs124.gundi.config;

import ibs124.gundi.model.application.Authority;

public abstract class AuthorityConfig {

    private static final String DELIMITER = "_";
    private static final String ROLE_AUTHORITY_PREFIX = "ROLE" + DELIMITER;
    private static final String FACTOR_AUTHORITY_PREFIX = "FACTOR" + DELIMITER;

    public static final Authority ROLE_ROOT = roleAuthorityOf("ROOT");

    public static final Authority ROLE_ADMIN = roleAuthorityOf("ADMIN");

    public static final Authority ROLE_USER = roleAuthorityOf("USER");

    public static final Authority[] DEFAULT_AUTHORITIES = {
            ROLE_ROOT,
            ROLE_ADMIN,
            ROLE_USER,
    };

    public static final Authority factorAuthorityOf(String name) {
        return () -> FACTOR_AUTHORITY_PREFIX + name;
    }

    private static final Authority roleAuthorityOf(String name) {
        return () -> ROLE_AUTHORITY_PREFIX + name;
    }
}
