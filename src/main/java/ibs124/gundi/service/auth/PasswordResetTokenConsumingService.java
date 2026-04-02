package ibs124.gundi.service.auth;

import ibs124.gundi.model.dto.auth.PasswordResetDto;
import ibs124.gundi.model.dto.auth.TokenDto;

public interface PasswordResetTokenConsumingService {

    TokenDto consume(PasswordResetDto request);

}