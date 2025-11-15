package ibs124.gundi.service.user;

import java.util.Optional;

import ibs124.gundi.model.dto.UserDetailsImpl;

public interface UserReadingService {

    Optional<UserDetailsImpl> findByUsernameOrEmail(String usernameOrEmail);

    boolean existsByUsername(String username);

}
