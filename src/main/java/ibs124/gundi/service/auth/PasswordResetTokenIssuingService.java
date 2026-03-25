package ibs124.gundi.service.auth;

import ibs124.gundi.model.dto.auth.TokenDto;

public interface PasswordResetTokenIssuingService {

    TokenDto issueByUsername(String username);

}