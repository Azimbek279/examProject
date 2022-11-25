package peaksoft.repository;

import peaksoft.model.Company;
import peaksoft.model.Instructor;
import peaksoft.model.Lesson;

import java.io.IOException;
import java.util.List;

public interface LessonRepository {
    void saveLesson(Long id,Lesson lesson) throws IOException;//

    void updateLesson(Lesson lesson,Long id);//

    List<Lesson> getAllLessons(Long courseId);//

    Lesson getLessonById(Long id);//

    void deleteLesson(Long id);//
}
