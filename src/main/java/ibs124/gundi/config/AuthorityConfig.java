package ibs124.gundi.config;

import ibs124.gundi.model.dto.auth.AuthorityContract;

public abstract class AuthorityConfig {

    private static final String DELIMITER = "_";
    private static final String ROLE_AUTHORITY_PREFIX = "ROLE" + DELIMITER;
    private static final String FACTOR_AUTHORITY_PREFIX = "FACTOR" + DELIMITER;

    public static final AuthorityContract ROLE_ROOT = roleAuthorityOf("ROOT");

    public static final AuthorityContract ROLE_ADMIN = roleAuthorityOf("ADMIN");

    public static final AuthorityContract ROLE_USER = roleAuthorityOf("USER");

    public static final AuthorityContract[] DEFAULT_AUTHORITIES = {
            ROLE_ROOT,
            ROLE_ADMIN,
            ROLE_USER,
    };

    public static final AuthorityContract factorAuthorityOf(String name) {
        return () -> FACTOR_AUTHORITY_PREFIX + name;
    }

    private static final AuthorityContract roleAuthorityOf(String name) {
        return () -> ROLE_AUTHORITY_PREFIX + name;
    }
}
