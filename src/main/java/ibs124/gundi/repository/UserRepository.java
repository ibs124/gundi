package ibs124.gundi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ibs124.gundi.model.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
