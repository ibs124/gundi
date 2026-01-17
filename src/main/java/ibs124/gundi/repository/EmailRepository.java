package ibs124.gundi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ibs124.gundi.model.domain.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

    boolean existsByEmailAddress(String email);

}
