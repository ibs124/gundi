package ibs124.gundi.service.auth;

import java.util.Collection;

import ibs124.gundi.model.application.contract.AuthorityContract;

public interface AuthorityCreatingService {

    Collection<? extends AuthorityContract> create();

}