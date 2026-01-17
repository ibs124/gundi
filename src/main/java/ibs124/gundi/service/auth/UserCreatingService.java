package ibs124.gundi.service.auth;

import ibs124.gundi.model.application.UserCreateDto;

public interface UserCreatingService {

    Long createUser(UserCreateDto request);

}
