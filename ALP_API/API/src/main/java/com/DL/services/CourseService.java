package src.main.java.com.DL.services;

import src.main.java.com.BL.entities.Course;
import src.main.java.com.BL.entities.Unit;
import src.main.java.com.DL.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {


    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getCourses() {
        return null;
    }

    public Optional<Unit> getCourseById(Long courseId) {
        return null;
    }

    public ResponseEntity<String> createNewCourse(Course course) {
        return null;
    }

    public ResponseEntity<String> updateCourse(Course course, Long courseId) {
        return null;
    }

    public ResponseEntity<String> deleteCourse(Long courseId) {
        return null;
    }
}
