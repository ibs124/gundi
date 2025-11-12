package ibs124.gundi.mapper;

import ibs124.gundi.model.api.RegisterRequest;
import ibs124.gundi.model.dto.RegisterDto;

public interface UserMapper {

    RegisterDto toServiceModel(RegisterRequest src);
}
