package peaksoft.service;

import peaksoft.model.Lesson;

import java.io.IOException;
import java.util.List;

public interface LessonService {
    void saveLesson(Long id, Lesson lesson) throws IOException;//

    void updateLesson(Lesson lesson,Long id);//

    List<Lesson> getAllLessons(Long id);//

    Lesson getLessonById(Long id);//

    void deleteLesson(Long id);//
}
