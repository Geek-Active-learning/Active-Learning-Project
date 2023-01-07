package com.example.demo.services;

import com.example.demo.entities.Course;
import com.example.demo.entities.Unit;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.RoleRepository;
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
