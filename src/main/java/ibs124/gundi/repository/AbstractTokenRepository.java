package ibs124.gundi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import ibs124.gundi.model.persistence.AbstractTokenEntity;
import ibs124.gundi.model.persistence.UserEntity;
import jakarta.validation.Valid;

import java.time.Instant;

@NoRepositoryBean
public interface AbstractTokenRepository<T extends AbstractTokenEntity>
        extends JpaRepository<T, Long> {

    Optional<@Valid T> findBySecret(String value);

    Optional<@Valid T> findBySecretAndExpiresAtAfter(String value, Instant expiresAt);

    Optional<@Valid T> findByUser(UserEntity user);

    Optional<@Valid T> findByUserId(Long id);

    Optional<@Valid T> findByUserUsernameOrUserPrimaryEmail(String username, String email);

    boolean existsBySecret(String value);

}
