package riwi.simulacro_SpringBoot.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import riwi.simulacro_SpringBoot.domain.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {
}
