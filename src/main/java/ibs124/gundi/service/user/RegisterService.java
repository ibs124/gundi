package ibs124.gundi.service.user;

import ibs124.gundi.model.dto.RegisterDto;
import ibs124.gundi.model.dto.RegisterResponseDto;

public interface RegisterService {

   RegisterResponseDto register(RegisterDto request);
   
}
