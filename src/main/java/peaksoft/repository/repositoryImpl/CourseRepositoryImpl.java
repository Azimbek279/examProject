package peaksoft.repository.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.model.Instructor;
import peaksoft.repository.CourseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Repository
@Transactional
public class CourseRepositoryImpl implements CourseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveCourse(Course course,Long id) throws IOException {
        if (course.getCourseName().toLowerCase().length()>0 &&
                course.getDescription().toLowerCase().length()>0) {
            for (Character i : course.getCourseName().toLowerCase().toCharArray()) {
                if (!Character.isLetter(i)) {
                    throw new IOException("no numbers in course name");
                }
            }
            for (Character i : course.getDescription().toLowerCase().toCharArray()) {
                if (!Character.isLetter(i)){
                    throw new IOException("no numbers in course description");
                }
            }
        }
        Company company = entityManager.find(Company.class, id);
        company.addCourse(course);
        course.setCompany(company);
        entityManager.merge(course);
    }

    @Override
    public void updateCourse(Long courseId,Course course) {
        Course course1 = entityManager.find(Course.class, courseId);
        course1.setCourseName(course.getCourseName());
        course1.setDuration(course.getDuration());
        course1.setDescription(course.getDescription());
        entityManager.merge(course1);
    }

    @Override
    public List<Course> getAllCourses(Long companyId) {
        return entityManager.createQuery("select c from Course c where " +
                        "c.company.id=:companyId", Course.class)
                .setParameter("companyId", companyId).getResultList();
    }

    @Override
    public Course getCourseById(Long id) {
        return entityManager.find(Course.class,id);
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = entityManager.find(Course.class, id);
        for (Instructor c : course.getInstructors()) {
            c.setCourses(null);
        }
        course.setCompany(null);

        entityManager.remove(course);
    }
}
