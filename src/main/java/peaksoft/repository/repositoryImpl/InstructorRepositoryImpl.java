package peaksoft.repository.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.model.Instructor;
import peaksoft.model.Student;
import peaksoft.repository.InstructorRepository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Repository
@Transactional
public class InstructorRepositoryImpl implements InstructorRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveInstructor(Long id, Instructor instructor)  throws IOException{
        if (instructor.getFirstName().toLowerCase().length()>2 && instructor.getLastName().toLowerCase().length()>2) {
            for (Character i : instructor.getFirstName().toLowerCase().toCharArray()) {
                if (!Character.isLetter(i)) {
                    throw new IOException("no numbers in company name");
                }
            }
            for (Character i : instructor.getLastName().toLowerCase().toCharArray()) {
                if (!Character.isLetter(i)) {
                    throw new IOException("no numbers in company located country");
                }
            }
        }
        Course course = entityManager.find(Course.class,id);
        if (course.getGroups()!=null){
            for (Group group : course.getGroups()) {
                for (Student student : group.getStudents()) {
                    instructor.plus();
                }
            }
        }
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
        return entityManager.createQuery("select g from Instructor g where g.courses.id = :id", Instructor.class).setParameter("id", courseId).getResultList();
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
