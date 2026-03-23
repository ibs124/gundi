package ibs124.gundi.repository;

import org.springframework.stereotype.Repository;

import ibs124.gundi.model.entity.VerificationTokenEntity;

@Repository
public interface VerificationTokenRepository
        extends AbstractTokenRepository<VerificationTokenEntity> {

}
