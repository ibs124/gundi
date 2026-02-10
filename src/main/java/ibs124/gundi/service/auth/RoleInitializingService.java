package ibs124.gundi.service.auth;

import java.util.Collection;

import ibs124.gundi.model.enumm.RoleName;

public interface RoleInitializingService {

    Collection<RoleName> initializeRoles();

}