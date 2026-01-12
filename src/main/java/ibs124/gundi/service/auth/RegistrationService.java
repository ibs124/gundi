package ibs124.gundi.service.auth;

import ibs124.gundi.model.dto.RegisterResponseDTO;
import ibs124.gundi.model.dto.UserCreateDTO;

public interface RegistrationService {

   RegisterResponseDTO register(UserCreateDTO dto, String appURL);
   
}
