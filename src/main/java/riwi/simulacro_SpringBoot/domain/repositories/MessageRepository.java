package riwi.simulacro_SpringBoot.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import riwi.simulacro_SpringBoot.domain.entities.Message;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
