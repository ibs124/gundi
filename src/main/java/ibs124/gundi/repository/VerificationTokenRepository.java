package ibs124.gundi.repository;

import org.springframework.stereotype.Repository;

import ibs124.gundi.model.persistence.VerificationToken;

@Repository
public interface VerificationTokenRepository
        extends AbstractTokenRepository<VerificationToken> {

}
