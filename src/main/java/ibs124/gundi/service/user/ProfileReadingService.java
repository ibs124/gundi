package ibs124.gundi.service.user;

import ibs124.gundi.model.dto.user.ProfileDto;

public interface ProfileReadingService {

    ProfileDto findById(Long id);

}