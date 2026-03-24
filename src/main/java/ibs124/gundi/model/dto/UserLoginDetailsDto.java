package ibs124.gundi.model.dto;

import java.util.Collection;

import jakarta.validation.constraints.NotNull;

@NotNull
public record UserLoginDetailsDto(
        Long id,
        Collection<AuthorityContract> authorities,
        String username,
        String password,
        String primaryEmail,
        String fullName,
        boolean isEnabled) {

}
