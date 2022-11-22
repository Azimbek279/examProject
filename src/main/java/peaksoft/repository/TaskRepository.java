package peaksoft.repository;

import peaksoft.model.Company;
import peaksoft.model.Student;
import peaksoft.model.Task;

import java.util.List;

public interface TaskRepository {
    void saveTask(Long id,Task task);//

    void updateTask(Task task,Long id);//

    List<Task> getAllTasks(Long id);//

    Task getTaskById(Long id);//

    void deleteTask(Long id);//
}
