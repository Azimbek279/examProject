package peaksoft.repository.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Group;
import peaksoft.model.Instructor;
import peaksoft.model.Student;
import peaksoft.repository.StudentRepository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class StudentRepositoryImpl implements StudentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveStudent(Long id,Student student) {
        Group group = entityManager.find(Group.class,id);
        group.addStudent(student);
        student.setGroup(group);
        entityManager.merge(student);
    }

    @Override
    public void updateStudent(Student student,Long id) {
        Student student1 = entityManager.find(Student.class,id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setEmail(student.getEmail());
        student1.setStudyFormat(student.getStudyFormat());
        entityManager.merge(student1);
    }

    @Override
    public List<Student> getAllStudents(Long groupId) {
        return entityManager.createQuery("from Student", Student.class).getResultList();
    }

    @Override
    public Student getStudentById(Long id) {
        return entityManager.find(Student.class,id);
    }

    @Override
    public void deleteStudent(Student student) {
        student.setGroup(null);
        entityManager.remove(entityManager.contains(student) ? student: entityManager.merge(student));
    }

    @Override
    public void assignStudent(Long groupId, Long studentId) {
        Student student = entityManager.find(Student.class,studentId);
        Group group = entityManager.find(Group.class,groupId);
        group.addStudent(student);
        student.setGroup(group);
        entityManager.merge(student);
    }
}
