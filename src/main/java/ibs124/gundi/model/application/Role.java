package ibs124.gundi.model.application;

public enum Role implements Authority {

    ROOT,

    ADMIN,

    USER;

    private static final String ROLE_AUTHORITY_PREFIX = "ROLE_";

    @Override
    public String getAuthority() {
        return ROLE_AUTHORITY_PREFIX + this.name();
    }

}
