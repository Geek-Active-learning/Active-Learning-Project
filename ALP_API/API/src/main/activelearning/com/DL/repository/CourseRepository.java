package activelearning.com.DL.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import activelearning.com.BL.entities.Course;


public interface CourseRepository extends JpaRepository<Course, Long> {
}