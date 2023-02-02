package activelearning.com.API.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import activelearning.com.BL.entities.Course;
import activelearning.com.BL.entities.Unit;
import activelearning.com.DL.services.CourseService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    @Autowired
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllCourses(){
        return this.courseService.getCourses();
    }

    @GetMapping("/{courseId}")
    public Optional<Unit> getCourseById(@PathVariable Long courseId){
        return this.courseService.getCourseById(courseId);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createNewCourse(@RequestBody Course course){
        return this.courseService.createNewCourse(course);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<String> updateCourse(@RequestBody Course course, @PathVariable Long courseId){
        return this.courseService.updateCourse(course,courseId);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId){
        return this.courseService.deleteCourse(courseId);
    }
}
