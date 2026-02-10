package ibs124.gundi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import ibs124.gundi.model.domain.AbstractToken;
import jakarta.validation.Valid;

import java.time.Instant;

@NoRepositoryBean
public interface AbstractTokenRepository<T extends AbstractToken>
        extends JpaRepository<T, Long> {

    Optional<@Valid T> findBySecretAndExpiresAtBefore(String value, Instant expiresAt);

    boolean existsBySecret(String value);

}
