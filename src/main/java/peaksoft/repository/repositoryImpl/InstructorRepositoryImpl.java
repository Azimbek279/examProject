package peaksoft.repository.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.model.Instructor;
import peaksoft.repository.InstructorRepository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class InstructorRepositoryImpl implements InstructorRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveInstructor(Long id, Instructor instructor) {
        Course course = entityManager.find(Course.class,id);
        course.addInstructor(instructor);
        instructor.setCourse(course);
        entityManager.merge(course);
    }

    @Override
    public void updateInstructor(Instructor instructor, Long id) {
        Instructor instructor1 = entityManager.find(Instructor.class,id);
        instructor1.setFirstName(instructor.getFirstName());
        instructor1.setLastName(instructor.getLastName());
        instructor1.setEmail(instructor.getEmail());
        entityManager.merge(instructor1);
    }

    @Override
    public List<Instructor> getAllInstructors(Long courseId) {
        return entityManager.createQuery("from Instructor", Instructor.class).getResultList();
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return entityManager.find(Instructor.class,id);
    }

    @Override
    public void deleteInstructor(Long id) {
        entityManager.remove(entityManager.find(Instructor.class,id));
    }

    @Override
    public void assignedInstructorToCourse(Long courseId, Long instructorId) {
        Instructor instructor = entityManager.find(Instructor.class,instructorId);
        Course course = entityManager.find(Course.class,courseId);
        instructor.setCourse(course);
        course.addInstructor(instructor);
        entityManager.merge(instructor);
        entityManager.merge(course);
    }
}
