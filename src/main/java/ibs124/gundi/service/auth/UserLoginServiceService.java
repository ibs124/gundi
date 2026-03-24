package ibs124.gundi.service.auth;

import ibs124.gundi.model.dto.UserLoginDetailsDto;

public interface UserLoginServiceService {

    UserLoginDetailsDto findByUsernameOrEmail(String request);

}