package ibs124.gundi.service.auth;

import ibs124.gundi.model.dto.auth.PasswordResetDto;
import ibs124.gundi.model.dto.auth.TokenContract;

public interface PasswordResetTokenConsumingService {

    TokenContract consume(PasswordResetDto request);

}