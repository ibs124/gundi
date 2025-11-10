package ibs124.gundi.mapper;

import ibs124.gundi.model.api.RegisterApiRequest;
import ibs124.gundi.model.dto.RegisterDto;

public interface UserMapper {

    RegisterDto toServiceModel(RegisterApiRequest src);
}
