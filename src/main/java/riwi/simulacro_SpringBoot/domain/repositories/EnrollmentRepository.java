package riwi.simulacro_SpringBoot.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import riwi.simulacro_SpringBoot.domain.entities.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {
}
