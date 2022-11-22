package peaksoft.repository;

import peaksoft.model.Company;
import peaksoft.model.Course;

import java.util.List;

public interface CourseRepository {
    void saveCourse(Course course,Long id);//

    void updateCourse(Long courseId,Course course);//

    List<Course> getAllCourses(Long companyId);//

    Course getCourseById(Long id);//

    void deleteCourse(Long id);//
}