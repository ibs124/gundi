package ibs124.gundi.service.auth;

import ibs124.gundi.model.dto.auth.PasswordResetDto;

public interface PasswordResetService {

    boolean resetPassword(PasswordResetDto request);

}