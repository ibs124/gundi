package ibs124.gundi.service.profile;

import ibs124.gundi.model.dto.profile.ProfileDto;

public interface ProfileReadingService {

    ProfileDto findById(Long id);

}