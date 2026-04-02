package ibs124.gundi.service.auth;

import ibs124.gundi.model.dto.auth.PasswordResetDto;

public interface PasswordResetTokenConsumingService {

    boolean consume(PasswordResetDto request);

}