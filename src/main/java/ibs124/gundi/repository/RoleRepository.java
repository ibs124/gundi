package ibs124.gundi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ibs124.gundi.model.domain.Role;
import ibs124.gundi.model.enumm.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findByNameIn(RoleName... names);
}
