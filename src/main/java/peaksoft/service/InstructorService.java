package peaksoft.service;

import peaksoft.model.Instructor;

import java.io.IOException;
import java.util.List;

public interface InstructorService {
    void saveInstructor(Long id, Instructor instructor) throws IOException;//

    void updateInstructor(Instructor instructor,Long id);//

    List<Instructor> getAllInstructors(Long id);//

    Instructor getInstructorById(Long id);//

    void deleteInstructor(Long id);//

    void assignedInstructorToCourse(Long courseId,Long instructorId);
}
