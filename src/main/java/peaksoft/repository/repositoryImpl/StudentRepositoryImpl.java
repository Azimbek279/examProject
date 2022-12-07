package peaksoft.repository.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.model.Instructor;
import peaksoft.model.Student;
import peaksoft.repository.StudentRepository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Repository
@Transactional
public class StudentRepositoryImpl implements StudentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> getAllStudents(Long id) {
        return entityManager.createQuery("select s from Student s where s.group.id = :id", Student.class).setParameter("id", id).getResultList();
    }

    @Override
    public void addStudent(Long id, Student student) throws IOException {
        if (student.getFirstName().toLowerCase().length()>0 && student.getLastName().toLowerCase().length()>0) {
            for (Character i : student.getFirstName().toLowerCase().toCharArray()) {
                if (!Character.isLetter(i)) {
                    throw new IOException("no numbers in student first name");
                }
            }
            for (Character i : student.getLastName().toLowerCase().toCharArray()) {
                if (!Character.isLetter(i)) {
                    throw new IOException("no numbers in student last name");
                }
            }
        }
        Group group = entityManager.find(Group.class, id);
        group.addStudent(student);
        student.setGroup(group);
        entityManager.merge(student);
        for (Course c:student.getGroup().getCourses()) {
            c.getCompany().plusStudent();
            for (Instructor i: c.getInstructors()) {
                i.plus();
            }
        }

    }

    @Override
    public Student getStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public void updateStudent(Student student, Long id) {
        Student student1 = entityManager.find(Student.class, id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setEmail(student.getEmail());
        student1.setStudyFormat(student.getStudyFormat());
        entityManager.merge(student1);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = entityManager.find(Student.class, id);

        for (Course course : student.getGroup().getCourses()) {
            course.getCompany().minusStudent();
            for (Instructor instructor : course.getInstructors()) {
                instructor.minus();
            }
        }
        student.setGroup(null);

        entityManager.remove(student);
    }

    @Override
    public void assignStudent(Long groupId, Long studentId) {
        Student student = entityManager.find(Student.class, studentId);
        Group group = entityManager.find(Group.class, groupId);
        group.addStudent(student);
        student.setGroup(group);
        entityManager.merge(student);
    }
}
