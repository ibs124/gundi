package ibs124.gundi.util;

import java.util.Set;

import ibs124.gundi.model.domain.Role;
import ibs124.gundi.model.domain.User;

public abstract class Users {

    public static final void addRoles(User user, Role... roles) {
        user.getRoles().addAll(Set.of(roles));
    }

    public static final void removeRoles(User user, Role... roles) {
        user.getRoles().removeAll(Set.of(roles));
    }
}
