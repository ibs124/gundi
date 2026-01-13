package ibs124.gundi.mapper;

import ibs124.gundi.model.application.EmailCreateDTO;
import ibs124.gundi.model.application.EmailDTO;
import ibs124.gundi.model.domain.Email;

public interface EmailMapper {

    Email toDomainModel(EmailCreateDTO src);

    EmailDTO toServiceModel(Email src);
}
