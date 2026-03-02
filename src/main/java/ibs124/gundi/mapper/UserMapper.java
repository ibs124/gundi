package ibs124.gundi.mapper;

import ibs124.gundi.model.application.RegisterDto;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.presentation.UserMeView;
import ibs124.gundi.model.presentation.UserRegisterRequest;
import ibs124.gundi.security.MyUserDetails;

public interface UserMapper {

    UserMeView mapToPresentationModel(MyUserDetails src);

    RegisterDto mapToApplicationModel(UserRegisterRequest src);

    MyUserDetails mapToSecurityModel(User src);

    User mapToDomainModel(RegisterDto dto);

}
