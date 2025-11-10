package ibs124.gundi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ibs124.gundi.model.domain.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}
