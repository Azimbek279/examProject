package peaksoft.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Course;
import peaksoft.repository.CourseRepository;
import peaksoft.service.CourseService;

import java.io.IOException;
import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void saveCourse(Course course,Long id) throws IOException {
        courseRepository.saveCourse(course,id);
    }

    @Override
    public void updateCourse(Long courseId,Course course) {
        courseRepository.updateCourse(courseId,course);
    }

    @Override
    public List<Course> getAllCourses(Long companyId) {
        return courseRepository.getAllCourses(companyId);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.getCourseById(id);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteCourse(id);
    }
}
