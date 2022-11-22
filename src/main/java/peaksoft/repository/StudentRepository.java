package peaksoft.repository;

import peaksoft.model.*;

import java.util.List;

public interface StudentRepository {
    void saveStudent(Long id,Student student);//

    void updateStudent(Student student,Long id);//

    List<Student> getAllStudents(Long groupId);//

    Student getStudentById(Long id);//

    void deleteStudent(Student student);//

    void assignStudent(Long groupId,Long studentId);//
}
