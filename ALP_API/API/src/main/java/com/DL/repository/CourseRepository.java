package src.main.java.com.DL.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import src.main.java.com.BL.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}