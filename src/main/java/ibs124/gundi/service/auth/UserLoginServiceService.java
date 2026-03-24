package ibs124.gundi.service.auth;

import ibs124.gundi.model.dto.auth.UserLoginDetailsDto;

public interface UserLoginServiceService {

    UserLoginDetailsDto findByUsernameOrEmail(String request);

}