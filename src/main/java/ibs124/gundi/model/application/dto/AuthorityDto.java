package ibs124.gundi.model.application.dto;

import ibs124.gundi.model.application.contract.AuthorityContract;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@NotNull
public record AuthorityDto(
        @PositiveOrZero long id,
        @NotBlank String name) implements AuthorityContract {

    @Override
    public String getAuthority() {
        return this.name();
    }

}
