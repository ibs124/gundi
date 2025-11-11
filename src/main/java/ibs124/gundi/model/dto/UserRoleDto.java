package ibs124.gundi.model.dto;

import java.time.Instant;

import ibs124.gundi.model.enumm.UserRoleType;

public record UserRoleDto(
        long id,
        Instant createdAt,
        Instant updatedAt,
        UserRoleType type) implements AbstractAuditableDto {
}
