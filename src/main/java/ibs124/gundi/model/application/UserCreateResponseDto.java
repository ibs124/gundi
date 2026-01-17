package ibs124.gundi.model.application;

import jakarta.validation.constraints.NotNull;

@NotNull
public record UserCreateResponseDto(
        long userId,
        String primaryEmail,
        String verificationToken) {

}
