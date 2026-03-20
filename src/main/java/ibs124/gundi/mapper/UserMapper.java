package ibs124.gundi.mapper;

import ibs124.gundi.model.application.dto.UserCreateDto;
import ibs124.gundi.model.application.dto.UserDto;
import ibs124.gundi.model.persistence.UserEntity;
import ibs124.gundi.model.presentation.ProfileSelfView;
import ibs124.gundi.model.presentation.RegisterRequest;
import ibs124.gundi.security.MyUserDetails;

public interface UserMapper {

    ProfileSelfView mapToPresentationModel(MyUserDetails src);

    UserCreateDto mapToApplicationModel(RegisterRequest src);

    UserDto mapToApplicationModel(UserEntity src);

    MyUserDetails mapToSecurityModel(UserEntity src);

    UserEntity mapToPersistenceModel(UserCreateDto dto);

}
