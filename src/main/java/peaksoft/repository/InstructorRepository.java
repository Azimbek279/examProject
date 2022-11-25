package peaksoft.repository;

import peaksoft.model.Company;
import peaksoft.model.Group;
import peaksoft.model.Instructor;

import java.io.IOException;
import java.util.List;

public interface InstructorRepository {

    void saveInstructor(Long id,Instructor instructor) throws IOException;//

    void updateInstructor(Instructor instructor,Long id);//

    List<Instructor> getAllInstructors(Long courseId);//

    Instructor getInstructorById(Long id);//

    void deleteInstructor(Long id);//

    void assignedInstructorToCourse(Long courseId,Long instructorId);
}
