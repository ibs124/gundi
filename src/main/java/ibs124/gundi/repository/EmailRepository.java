package ibs124.gundi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ibs124.gundi.model.persistence.EmailEntity;

@Repository
public interface EmailRepository extends JpaRepository<EmailEntity, Long> {

    boolean existsByEmailAddress(String email);

}
