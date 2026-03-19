package ibs124.gundi.model.application;

import jakarta.validation.constraints.NotBlank;

public record FactorAuthority(@NotBlank String name) implements Authority {

    public static final String FACTOR_AUTHORITY_PREFIX = "FACTOR_";

    public static final FactorAuthority NEW_USER = new FactorAuthority("NEW_USER");

    public static final FactorAuthority[] DEFAULT_VALUES = { NEW_USER };

    @Override
    public String getAuthority() {
        return FACTOR_AUTHORITY_PREFIX + this.name();
    }
}