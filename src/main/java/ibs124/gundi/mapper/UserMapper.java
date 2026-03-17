package ibs124.gundi.mapper;

import ibs124.gundi.model.application.RegisterDto;
import ibs124.gundi.model.persistence.User;
import ibs124.gundi.model.presentation.ProfileSelfView;
import ibs124.gundi.model.presentation.RegisterRequest;
import ibs124.gundi.security.MyUserDetails;

public interface UserMapper {

    ProfileSelfView mapToPresentationModel(MyUserDetails src);

    RegisterDto mapToApplicationModel(RegisterRequest src);

    MyUserDetails mapToSecurityModel(User src);

    User mapToPersistenceModel(RegisterDto dto);

}
