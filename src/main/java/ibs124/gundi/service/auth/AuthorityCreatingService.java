package ibs124.gundi.service.auth;

import java.util.Collection;

import ibs124.gundi.model.dto.AuthorityContract;

public interface AuthorityCreatingService {

    Collection<? extends AuthorityContract> create();

}