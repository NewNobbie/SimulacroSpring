package riwi.simulacro_SpringBoot.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import riwi.simulacro_SpringBoot.domain.entities.Lesson;

public interface LessonRepository  extends JpaRepository<Lesson,Long> {

}
