package riwi.simulacro_SpringBoot.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import riwi.simulacro_SpringBoot.domain.entities.Courses;

// 4
@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long> {
    
}
