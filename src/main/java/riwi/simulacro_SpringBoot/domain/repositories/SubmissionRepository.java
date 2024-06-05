package riwi.simulacro_SpringBoot.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import riwi.simulacro_SpringBoot.domain.entities.Submission;

public interface SubmissionRepository extends JpaRepository<Submission,Long> {
}
