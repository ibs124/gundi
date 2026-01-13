package ibs124.gundi.service.auth.application;

import ibs124.gundi.model.application.RegisterDTO;
import ibs124.gundi.model.application.RegisterResponseDTO;

public interface RegistrationService {

   RegisterResponseDTO register(RegisterDTO dto, String appUrl);
   
}
