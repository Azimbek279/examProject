package peaksoft.service;

import peaksoft.model.Instructor;

import java.util.List;

public interface InstructorService {
    void saveInstructor(Long id, Instructor instructor);//

    void updateInstructor(Instructor instructor,Long id);//

    List<Instructor> getAllInstructors(Long id);//

    Instructor getInstructorById(Long id);//

    void deleteInstructor(Long id);//

    void assignedInstructorToCourse(Long courseId,Long instructorId);
}
