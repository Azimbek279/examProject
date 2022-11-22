package peaksoft.service;

import peaksoft.model.Student;

import java.util.List;

public interface StudentService {
    void saveStudent(Long id, Student student);//

    void updateStudent(Student student,Long id);//

    List<Student> getAllStudents(Long groupId);//

    Student getStudentById(Long id);//

    void deleteStudent(Student student);//

    void assignStudent(Long groupId,Long studentId);//
}
