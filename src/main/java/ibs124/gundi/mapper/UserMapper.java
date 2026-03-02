package ibs124.gundi.mapper;

import ibs124.gundi.model.application.RegisterDto;
import ibs124.gundi.model.application.UserDetailsDto;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.presentation.UserMeView;
import ibs124.gundi.model.presentation.UserRegisterRequest;

public interface UserMapper {

    UserMeView mapToPresentationModel(UserDetailsDto src);

    RegisterDto mapToApplicationModel(UserRegisterRequest src);

    UserDetailsDto mapToSecurityModel(User src);

    User mapToDomainModel(RegisterDto dto);

}
