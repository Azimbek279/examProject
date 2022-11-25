package peaksoft.repository.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Course;
import peaksoft.model.Lesson;
import peaksoft.repository.LessonRepository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Repository
@Transactional
public class LessonRepositoryImpl implements LessonRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveLesson(Long id,Lesson lesson) throws IOException {
        if (lesson.getLessonName().toLowerCase().length()>0) {
            for (Character i : lesson.getLessonName().toLowerCase().toCharArray()) {
                if (!Character.isLetter(i)) {
                    throw new IOException("no numbers in lesson name!");
                }
            }
        }
        Course course = entityManager.find(Course.class,id);
        course.addLesson(lesson);
        lesson.setCourse(course);
        entityManager.merge(lesson);
    }
    @Override
    public void updateLesson(Lesson lesson,Long id) {
        Lesson lesson1 = entityManager.find(Lesson.class,id);
        lesson1.setLessonName(lesson.getLessonName());
        entityManager.merge(lesson1);
    }

    @Override
    public List<Lesson> getAllLessons(Long courseId) {
        return entityManager.createQuery("select l from Lesson  l where l.course.id=:courseId",
                Lesson.class).setParameter("courseId", courseId).getResultList();
    }

    @Override
    public Lesson getLessonById(Long id) {
        return entityManager.find(Lesson.class,id);
    }

    @Override
    public void deleteLesson(Long id) {
        Lesson lesson = entityManager.find(Lesson.class, id);
        lesson.setCourse(null);
        entityManager.remove(lesson);
    }
}
