package ibs124.gundi.service.auth;

import java.util.Collection;

import ibs124.gundi.model.application.dto.AuthorityDto;

public interface AuthorityInitService {

    Collection<AuthorityDto> init();

}