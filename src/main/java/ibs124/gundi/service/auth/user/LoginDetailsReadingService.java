package ibs124.gundi.service.auth.user;

import ibs124.gundi.model.dto.auth.UserLoginDetailsDto;

public interface LoginDetailsReadingService {

    UserLoginDetailsDto findByUsernameOrEmail(String request);

}