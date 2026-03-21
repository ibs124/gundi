package ibs124.gundi.service.auth;

import java.util.Collection;

import ibs124.gundi.model.application.Authority;

public interface AuthorityCreatingService {

    Collection<? extends Authority> create();

}