package ibs124.gundi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import ibs124.gundi.model.persistence.AbstractToken;
import ibs124.gundi.model.persistence.User;
import jakarta.validation.Valid;

import java.time.Instant;

@NoRepositoryBean
public interface AbstractTokenRepository<T extends AbstractToken>
        extends JpaRepository<T, Long> {

    Optional<@Valid T> findBySecret(String value);

    Optional<@Valid T> findBySecretAndExpiresAtAfter(String value, Instant expiresAt);

    Optional<@Valid T> findByUser(User user);

    Optional<@Valid T> findByUserId(Long id);

    boolean existsBySecret(String value);

}
