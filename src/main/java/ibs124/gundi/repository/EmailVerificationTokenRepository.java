package ibs124.gundi.repository;

import org.springframework.stereotype.Repository;

import ibs124.gundi.model.entity.EmailVerificationTokenEntity;

@Repository
public interface EmailVerificationTokenRepository
        extends AbstractTokenRepository<EmailVerificationTokenEntity> {

}
